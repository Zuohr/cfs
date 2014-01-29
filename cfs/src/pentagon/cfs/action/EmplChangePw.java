/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.dao.EmployeeDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.ChangepwForm;
import pentagon.cfs.model.Model;

public class EmplChangePw implements Action {
	private Model model;

	public EmplChangePw(Model model) {
		this.model = model;
	}

	@Override
	public String getName() {
		return "emplchangepw.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute(
				"employee");

		if (employee == null) {
			return "login.jsp";
		}
		
		
		request.setAttribute("nav_eechangepw", "active");
		request.setAttribute("header_type", "Employee");
		request.setAttribute("header_name", employee.getFirstname() + " "
				+ employee.getLastname());

		if ("submit".equals(request.getParameter("eechangepw_btn"))) {
			//TODO
			try {
				Transaction.begin();
			// refresh user
			EmployeeDAO employeeDAO = model.getEmployeeDAO();
			employee = employeeDAO.read(employee.getId());
			request.getSession().setAttribute("employee", employee);
			ChangepwForm form = new ChangepwForm(request);
			
			if (form.isComplete()) {
				if (employee.getPassword().equals(form.getOldPassword())) {
					employee.setPassword(form.getNewPassword());

					employeeDAO.update(employee);

					request.setAttribute("op_success", String.format(
							"Password changed for %s %s.",
							employee.getFirstname(), employee.getLastname()));
				} else {
					request.setAttribute("op_fail",
							"Your Old Password is wrong! ");
				}
			} else {
				request.setAttribute("op_fail", "Password change failed!");
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
			}
			//TODO
			Transaction.commit();
			} catch (RollbackException e) {
				if (Transaction.isActive()) {
					Transaction.rollback();
				}
			}
			return "ee_changepw.jsp";
		} else if ("cancel".equals(request.getParameter("eechangepw_btn"))) {
			return "emplviewcmlist.do";
		} else {
			return "ee_changepw.jsp";
		}
	}
}

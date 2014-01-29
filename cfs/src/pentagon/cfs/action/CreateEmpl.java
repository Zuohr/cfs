/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.dao.EmployeeDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.CreateEmplForm;
import pentagon.cfs.model.Model;

public class CreateEmpl implements Action {

	private EmployeeDAO employeeDAO;
	private Model model;

	public CreateEmpl(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute(
				"employee");
		if (employee == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_eecreateempl", "active");
		request.setAttribute("header_type", "Employee");
		request.setAttribute("header_name", employee.getFirstname() + " "
				+ employee.getLastname());
		if ("submit".equals(request.getParameter("createee_btn"))) {
			// TODO
			try {
				Transaction.begin();

				CreateEmplForm form = new CreateEmplForm(request);
				if (form.isComplete()) {
					Employee newEmployee = form.getEmployeeBean();
					employeeDAO = model.getEmployeeDAO();
					if (employeeDAO.createEmployee(newEmployee)) {
						request.setAttribute("op_success", "Employee: "
								+ newEmployee.getUsername() + " created.");
					} else {
						request.setAttribute("op_fail",
								"Employee creation failed, user already exist.");
					}
				} else {
					request.setAttribute("op_fail", "Employee creation failed.");
					request.setAttribute("errors", form.getErrors());
				}
				// TODO
				Transaction.commit();
			} catch (RollbackException e) {
				if (Transaction.isActive()) {
					Transaction.rollback();
				}
			}

			return "ee_createempl.jsp";
		} else if ("cancel".equals(request.getParameter("createee_btn"))) {
			return "emplviewcmlist.do";
		} else {
			return "ee_createempl.jsp";
		}
	}

	@Override
	public String getName() {
		return "createempl.do";
	}

}

package pentagon.cfs.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pentagon.cfs.model.Model;
import pentagon.cfs.dao.EmployeeDAO;
import org.genericdao.RollbackException;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.ChangepwForm;

public class EmplChangePw implements Action {
	private Model model;
	

	public EmplChangePw(Model model) {
		
		this.model = model;
	}

	public String getName() {
		return "emplchangepw.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		if (employee == null) {
			return "login.do";
		} else {
			if ("submit".equals(request.getParameter("eechangepw_btn"))) {
				ChangepwForm form = new ChangepwForm(request);

				if (form.isComplete()) {
					if (employee.getPassword() == form.getnewPassword()) {
						employee.setPassword(form.getnewPassword());
						employee.setId(employee.getId());

						EmployeeDAO employeeDAO = model.getEmployeeDAO();

						try {
							employeeDAO.updateEmployee(employee);
						} catch (RollbackException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						request.setAttribute(
								"result",
								"Password changed for "
										+ employee.getFirstname() + " "
										+ employee.getLastname());
						return "ee_changepw.jsp";
					} else {
						request.setAttribute("result",
								"Your Old Password is wrong! ");
						return "ee_changepw.jsp";
					}

				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "ee_changepw.jsp";
				}
			} else if ("cancel".equals(request.getParameter("cancel_btn"))) {
				return "emplchangepw.do";
			} else {
				return "ee_changepw.jsp";
			}
		}
	}
}

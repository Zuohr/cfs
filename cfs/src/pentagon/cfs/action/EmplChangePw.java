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

	@Override
	public String getName() {
		return "emplchangepw.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		if (employee == null) {
			return "login.jsp";
		} else {
			request.setAttribute("nav_eechangepw", "active");
			if ("submit".equals(request.getParameter("eechangepw_btn"))) {
				ChangepwForm form = new ChangepwForm(request);

				if (form.isComplete()) {
					if (employee.getPassword().equals(form.getOldPassword())) {
						employee.setPassword(form.getNewPassword());

						EmployeeDAO employeeDAO = model.getEmployeeDAO();
						employeeDAO.update(employee);

						request.setAttribute("result",
								String.format("Password changed for %s %s.",
										employee.getFirstname(),
										employee.getLastname()));
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
			} else if ("cancel".equals(request.getParameter("cmchangepw_btn"))) {
				return "emplviewcmlist.do";
			} else {
				return "ee_changepw.jsp";
			}
		}
	}
}

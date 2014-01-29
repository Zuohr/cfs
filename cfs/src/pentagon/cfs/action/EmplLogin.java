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

import pentagon.cfs.dao.EmployeeDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.EmplLoginForm;
import pentagon.cfs.model.Model;

public class EmplLogin implements Action {
	private EmployeeDAO employeeDAO;

	public EmplLogin(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if(employee != null) {
			return "emplviewcmlist.do";
		}
		
		String role = request.getParameter("submit");
		if (role == null) {
			return "login.jsp";
		} else if (role.startsWith("em")) {
			EmplLoginForm form = new EmplLoginForm(request);
			if (form.isComplete()) {
				Employee ee = employeeDAO.getProfile(form.getUserName());
				if (ee != null) {
					if (ee.getPassword().equals(form.getPassword())) {
						request.getSession().setAttribute("employee", ee);
						return "emplviewcmlist.do";
					} else {
						request.setAttribute("result", "Password is not correct");
						return "login.jsp";
					}
				} else {
					request.setAttribute("result", "User name is not correct");
					return "login.jsp";
				}
			} else {
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
				return "login.jsp";
			}
		} else {
			return "login.jsp";
		}
	}

	@Override
	public String getName() {
		return "empllogin.do";
	}

}

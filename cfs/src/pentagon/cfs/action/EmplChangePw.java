package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import pentagon.cfs.model.Model;
import pentagon.cfs.dao.EmployeeDAO;
import org.genericdao.RollbackException;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.ChangepwForm;

public class EmplChangePw implements Action {

	private EmployeeDAO EmployeeDAO;

	public EmplChangePw(Model model) {
		EmployeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "emplchangepw.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		Employee employee = (Employee) request.getSession().getAttribute("employee");

		if (employee == null) {
			return "login.do";
		} else {

			ChangepwForm form = new ChangepwForm(request);

			String newPassword = form.getnewPassword();
			employee.setPassword(newPassword);
			

			try {
				// Change the password
				EmployeeDAO.updateEmployee(employee);

				request.setAttribute("errors", "Password changed for "
						+ employee.getFirstname() + " " + employee.getLastname());
				return "ee_changepw.jsp";
			} catch (RollbackException e) {
				errors.add(e.toString());
				return "ee_changepw.jsp";//empl_changepw.jsp
			}

		}
	}
}

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.EmployeeDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.CreateEmplForm;
import pentagon.cfs.model.Model;

public class CreateEmpl implements Action {

	private EmployeeDAO employeeDAO;

	public CreateEmpl(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		if ("submit".equals(request.getParameter("createee_btn"))) {
			CreateEmplForm form = new CreateEmplForm(request);
			if (form.isComplete()) {
				Employee newEmployee = form.getEmployeeBean();
				if (employeeDAO.createEmployee(newEmployee)) {
					request.setAttribute("result",
							"Employee: " + newEmployee.getUsername() + " created.");
				} else {
					request.setAttribute("result",
							"Employee creation failed, user already exist.");
				}
				return "ee_createempl.jsp";
			} else {
				request.setAttribute("errors", form.getErrors());
				return "ee_createempl.jsp";
			}
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

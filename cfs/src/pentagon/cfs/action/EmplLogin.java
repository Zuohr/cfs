//shidong
package pentagon.cfs.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
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
		// TODO Auto-generated method stub
		String role = request.getQueryString();
		System.out.println(role);
		if(role==null){
			return "login.jsp";
		}else if(role.startsWith("em")){
			EmplLoginForm form = new EmplLoginForm(request);
			form.getUserName();
			form.getPassword();
//			if(form.isComplete()) {
//				Employee Ee = employeeDAO.getProfile(form.getUserName());
//			}
		}
		return "login.jsp";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "empllogin.do";
	}

}

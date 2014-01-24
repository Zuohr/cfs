//shidong
package pentagon.cfs.action;

import java.sql.SQLException;
import java.util.ArrayList;

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
		String role = request.getParameter("submit");
		System.out.println(role);
		if(role==null){
			return "login.jsp";
		}else if(role.startsWith("em")){
			EmplLoginForm form = new EmplLoginForm(request);
			if(form.isComplete()) {
				if(employeeDAO.getProfile(form.getUserName())!=null){
					Employee ee = employeeDAO.getProfile(form.getUserName());
					if(ee.getPassword().equals(form.getPassword())) {
						request.setAttribute("employeeUsername", form.getUserName());

						return "empl_main.jsp";
					}else {
						request.setAttribute("result", "Password not correct");	
						return "login.jsp";
					}
				}else{
					request.setAttribute("result", "Non-exist user");

					return "login.jsp";
				}
			}else{
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					System.out.println(errors);
					return "login.jsp";			
				}
			}else{
				System.out.println("5");

				return "login.jsp";
			}
			
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "empllogin.do";
	}

}

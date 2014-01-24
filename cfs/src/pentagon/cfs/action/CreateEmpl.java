//shidong
package pentagon.cfs.action;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.action.Action;
import pentagon.cfs.model.*;
import pentagon.cfs.dao.*;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.CreateCmForm;
import pentagon.cfs.formbean.CreateEmplForm;

public class CreateEmpl implements Action {

	

	private EmployeeDAO employeeDAO;

	public CreateEmpl(Model model) {
		employeeDAO = model.getEmployeeDAO();

	}	
	
	@Override
	public String perform(HttpServletRequest request) throws RollbackException{
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("createee_btn"));
	if ("submit".equals(request.getParameter("createee_btn"))) {
			CreateEmplForm form = new CreateEmplForm(request);
			if(form.isComplete()) {
			    System.out.println("3");
				Employee newEe = form.getEmployeeBean();	
				if(employeeDAO.createEmployee(newEe)){
					request.setAttribute("result", "Employee:" + newEe.getUsername() 
							+ "created.");
				}else{
					request.setAttribute("result",
							"Employee creation failed, Employee already exist.");
				}
				return "ee_createempl.jsp";
			}else{
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
				return "ee_createempl.jsp";			
			}
		}else{
			System.out.println("234");

			return "ee_createempl.jsp";
		}
}//System.out.println(form.getCustomerBean().getAddr1().toString());
	
		

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "createempl.do";
	}

}

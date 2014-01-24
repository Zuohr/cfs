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
import pentagon.cfs.formbean.CreateCmForm;

public class CreateCustomer implements Action {

	

	private CustomerDAO customerDAO;

	public CreateCustomer(Model model) {
		customerDAO = model.getCustomerDAO();

	}	
	
	@Override
	public String perform(HttpServletRequest request) throws RollbackException{
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("createcm_btn"));
	if ("submit".equals(request.getParameter("createcm_btn"))) {
			CreateCmForm form = new CreateCmForm(request);
			if(form.isComplete()) {
			    System.out.println("3");
				Customer newCm = form.getCustomerBean();	
				if(customerDAO.createCustomer(newCm)){
					request.setAttribute("result", "Customer:" + newCm.getUsername() 
							+ "created.");
				}else{
					request.setAttribute("result",
							"Customer creation failed, Customer already exist.");
				}
				return "ee_createcm.jsp";
			}else{
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
				return "ee_createcm.jsp";			
			}
		}else{
			System.out.println("234");

			return "ee_createcm.jsp";
		}
}//System.out.println(form.getCustomerBean().getAddr1().toString());
	
		

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "createcm.do";
	}

}

package pentagon.cfs.action;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.action.Action;
import pentagon.cfs.model.*;
import pentagon.cfs.dao.*;
import pentagon.cfs.formbean.CreateCmForm;

public class CreateCustomer implements Action {

	

	private CustomerDAO customerDAO;

	public CreateCustomer(Model model) {
		customerDAO = model.getCustomerDAO();

	}	
	
	@Override
	public String perform(HttpServletRequest request){
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
   		CreateCmForm form = new CreateCmForm(request);
   		if(form.getErrors().size()!=0) {
   			errors.addAll(form.getErrors());
   			return "ee_createcm.jsp";
   		}else{
   			try {
				customerDAO.create(form.getCustomerBean());
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   			
   		}
   		//System.out.println(form.getCustomerBean().getAddr1().toString());
   		
    	return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "createCustomer.do";
	}

}

package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.formbean.CmLoginForm;
import pentagon.cfs.model.Model;

public class CmLogin implements Action {
	private CustomerDAO customerDAO;

	public CmLogin(Model model) {
		customerDAO = model.getCustomerDAO();

	}	
	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
   		CmLoginForm form = new CmLoginForm(request);
   		Customer cm = new Customer();
		try {
			cm = customerDAO.getProfile(form.getUserName());
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!cm.getPassword().equals(form.getPassword())){
			errors.add("Password not correct!");
		}
		
   		if(form.getErrors().size()!=0) {
   			errors.addAll(form.getErrors());
   			return "ee_createcm.jsp";
   		}else{
   			return "";
   		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "cmLogin.do";

	}

}

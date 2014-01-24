//shidong
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
		return "login.jsp";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "cmlogin.do";

	}

}

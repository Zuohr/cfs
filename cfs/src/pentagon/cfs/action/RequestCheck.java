//wc
package pentagon.cfs.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.model.Model;

public class RequestCheck implements Action {

	private CustomerDAO customerDAO;

	public RequestCheck(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "RequestCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

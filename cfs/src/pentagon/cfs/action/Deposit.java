//wc
package pentagon.cfs.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.model.Model;

public class Deposit implements Action {
	private CustomerDAO customerDAO;

	public Deposit(Model model) {
		customerDAO = model.getCustomerDAO();

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Deposit.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

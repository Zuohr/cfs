/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import pentagon.cfs.databean.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
	public CustomerDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(Customer.class, tableName, cp);
	}

	public boolean createCustomer(Customer customer) throws RollbackException {
		if (customer != null && getProfile(customer.getUsername()) == null) {
			create(customer);
			return true;
		} else {
			return false;
		}
	}

	public Customer getProfile(String username) throws RollbackException {
		if (username == null) {
			return null;
		}
		Customer[] result = match(MatchArg.equals("username", username));
		if (result.length > 0) {
			return result[0];
		} else {
			return null;
		}
	}

	public void updateCustomer(Customer customer) throws RollbackException {
		if (customer != null && getProfile(customer.getUsername()) != null) {
			update(customer);
		}
	}

	public Customer[] getAll() throws RollbackException {
		return match();
	}

}

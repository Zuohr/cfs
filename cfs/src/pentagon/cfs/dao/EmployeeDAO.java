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

import pentagon.cfs.databean.Employee;

public class EmployeeDAO extends GenericDAO<Employee> {
	public EmployeeDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(Employee.class, tableName, cp);
	}

	public boolean createEmployee(Employee employee) throws RollbackException {
		if (employee != null && getProfile(employee.getUsername()) == null) {
			create(employee);
			return true;
		} else {
			return false;
		}
	}

	public Employee getProfile(String username) throws RollbackException {
		if (username == null) {
			return null;
		}
		Employee[] result = match(MatchArg.equals("username", username));
		if (result.length > 0) {
			return result[0];
		} else {
			return null;
		}
	}

	public void updateEmployee(Employee employee) throws RollbackException {
		if (employee != null && getProfile(employee.getUsername()) != null) {
			update(employee);
		}
	}
}

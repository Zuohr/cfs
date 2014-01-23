package pentagon.cfs.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.EmployeeDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.MetaDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.dao.TransactionDAO;

public class Model {
	private ConnectionPool cp;
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private MetaDAO metaDAO;

	private PositionDAO positionDAO;

	private TransactionDAO transactionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			cp = new ConnectionPool(config.getInitParameter("jdbcName"),
					config.getInitParameter("jdbcURL"));
			this.customerDAO = new CustomerDAO("cfs_customer", cp);
			this.employeeDAO = new EmployeeDAO("cfs_employee", cp);
			this.fundDAO = new FundDAO("cfs_fund", cp);
			this.fundPriceHistoryDAO = new FundPriceHistoryDAO("cfs_fundprice",
					cp);
			this.positionDAO = new PositionDAO("cfs_position", cp);
			this.transactionDAO = new TransactionDAO("cfs_transaction", cp);
			this.metaDAO = new MetaDAO("cfs_meta", cp);
		} catch (DAOException e) {
			throw new ServletException();
		}
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public FundPriceHistoryDAO getFundPriceHistoryDAO() {
		return fundPriceHistoryDAO;
	}

	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public MetaDAO getMetaDAO() {
		return metaDAO;
	}
}

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
import org.genericdao.Transaction;

import pentagon.cfs.databean.Fund;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(Fund.class, tableName, cp);
	}

	public boolean createFund(Fund fund) throws RollbackException {
		try {
			Transaction.begin();
			if (fund == null
					|| match(MatchArg.or(
							MatchArg.equals("name", fund.getName()),
							MatchArg.equals("symbol", fund.getSymbol()))).length > 0) {
				return false;
			} else {
				create(fund);
			}
			Transaction.commit();
			return true;
		} finally {
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
}
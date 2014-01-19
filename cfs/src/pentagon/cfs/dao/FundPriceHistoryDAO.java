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

import pentagon.cfs.databean.FundPriceHistory;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistory>{
	public FundPriceHistoryDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(FundPriceHistory.class, tableName, cp);
	}
	
	public FundPriceHistory[] getHistory(int fund_id) throws RollbackException {
		return match(MatchArg.equals("fund_id", Integer.valueOf(fund_id)));
	}
}

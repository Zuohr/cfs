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

import pentagon.cfs.databean.TransactionRecord;

public class TransactionDAO extends GenericDAO<TransactionRecord> {
	public TransactionDAO(String tableName, ConnectionPool cp)
			throws DAOException {
		super(TransactionRecord.class, tableName, cp);
	}

	public TransactionRecord[] getPending() throws RollbackException {
		return match(MatchArg.equals("complete", Boolean.valueOf(false)));
	}
	
	public TransactionRecord[] getHistory(int cm_id) throws RollbackException {
		return match(MatchArg.equals("cm_id", Integer.valueOf(cm_id)));
	}
	
}

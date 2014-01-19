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

import pentagon.cfs.databean.Position;

public class PositionDAO extends GenericDAO<Position> {
	public PositionDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(Position.class, tableName, cp);
	}

	public Position[] getPositions(int cm_id) throws RollbackException {
		return match(MatchArg.equals("cm_id", Integer.valueOf(cm_id)));
	}

}

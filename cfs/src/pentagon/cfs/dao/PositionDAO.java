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

	public void updatePosition(int cm_id, int fund_id, long share) throws RollbackException {
		Position[] positions = match(MatchArg.and(
				MatchArg.equals("cm_id", cm_id),
				MatchArg.equals("fund_id", fund_id)));
		if(positions.length == 0) {
			Position pos = new Position();
			pos.setCm_id(cm_id);
			pos.setFund_id(fund_id);
			pos.setShare(share);
			create(pos);
		} else {
			Position pos = positions[0];
			Long currShare = pos.getShare();
			if(currShare + share == 0) {
				delete(pos);
			} else {
				pos.setShare(currShare + share);
				update(pos);
			}
		}
	}
}

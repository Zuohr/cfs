package pentagon.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import pentagon.cfs.databean.Meta;

public class MetaDAO extends GenericDAO<Meta> {
	public MetaDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(Meta.class, tableName, cp);
	}
}

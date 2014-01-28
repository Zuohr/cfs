package pentagon.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.databean.TestBean;

public class TestBeanDAO extends GenericDAO<TestBean> {
	public TestBeanDAO(ConnectionPool cp) throws DAOException {
		super(TestBean.class, "test", cp);
	}
	
	public void bundle() throws RollbackException {
		try {
			Transaction.begin();
			
			TestBean b = new TestBean();
			b.setA(1);
			b.setB(2);
			create(b);
			b = read(Integer.valueOf(1));
			b.setA(3);
			update(b);
			
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
		} finally {
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
		}
		
//		TestBean b = new TestBean();
//		b.setA(1);
//		b.setB(2);
//		create(b);
//		b = read(Integer.valueOf(1));
//		b.setA(3);
//		update(b);
	}
}

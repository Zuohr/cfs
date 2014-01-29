package pentagon.cfs.action;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class Sample {
	public static void main(String[] args) {
		try {
			Transaction.begin();

			Transaction.commit();
		} catch (RollbackException e) {
			//
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
}

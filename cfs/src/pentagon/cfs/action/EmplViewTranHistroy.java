//wc
package pentagon.cfs.action;



import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.model.Model;

public class EmplViewTranHistroy implements Action {
	private TransactionDAO transactionDAO;

	public EmplViewTranHistroy(Model model) {
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "EmplViewTranHistroy.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		
		
		
		
		return null;
		
	}

}

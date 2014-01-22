//wc
package pentagon.cfs.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.DepositForm;
import pentagon.cfs.model.Model;

public class Deposit implements Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public Deposit(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO=model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Deposit.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		DepositForm form = new DepositForm(request);
		
		
		long deposit= form.getDeposit();
		
		TransactionRecord record = new TransactionRecord();
				record.setCm_id(form.getId());
				record.setAmount(deposit);
				record.setComplete(false);
				record.setType("deposit");   // need to complete
				
		try {
			transactionDAO.create(record);
			
			request.setAttribute("errors", "Add " + form.getDeposit() + "to "
					+ form.getUserName()
					+ "  successfully!");
			return "ee_depositcheck.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ee_depositcheck.jsp";
		}
			

			

		
	}

}

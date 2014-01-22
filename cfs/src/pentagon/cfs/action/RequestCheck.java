//wc
package pentagon.cfs.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.DepositForm;
import pentagon.cfs.formbean.ReqcheckForm;
import pentagon.cfs.model.Model;

public class RequestCheck implements Action {

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	
	public RequestCheck(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO=model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "RequestCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		ReqcheckForm form = new ReqcheckForm(request);
		
		Customer customer = (Customer) request.getSession()
				.getAttribute("user");
		
		long check = form.getCheck();
		long cash = customer.getCash();
		if (check<=cash){
		TransactionRecord record = new TransactionRecord();
				record.setCm_id(customer.getId());
				record.setAmount(check*-1);
				record.setComplete(false);   
				record.setType("withdraw");
				
		try {
			transactionDAO.create(record);
			
			request.setAttribute("errors", "Withdraw " + form.getCheck() + "from "
					+ customer.getLastname() + "," + customer.getFirstname()
					+ "  successfully!");
			return "cm_requestcheck.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else{
			request.setAttribute("errors", "Your current balance is not enough , please try later or decrease the amount !");
			return "cm_requestcheck.jsp";
		
		

		
	}
		return "cm_requestcheck.jsp";
	}}



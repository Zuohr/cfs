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
import pentagon.cfs.formbean.ReqcheckForm;
import pentagon.cfs.model.Model;

public class RequestCheck implements Action {

	private Model model;
	private TransactionDAO transactionDAO;

	public RequestCheck(Model model) {

		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "requestcheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		

		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		if (customer == null) {
			return "login.do";
		} else {
			
			
			
			if ("submit".equals(request.getParameter("requestcheck_btn"))) {
				
				ReqcheckForm form = new ReqcheckForm(request);
				
				long check = form.getCheck();
				long cash = customer.getCash();
				if (check <= cash) {
					TransactionRecord record = new TransactionRecord();
					record.setCm_id(customer.getId());
					record.setAmount(check);
					record.setComplete(false);
					record.setType("withdraw");

					try {
						transactionDAO.create(record);
						
						request.setAttribute("errors",
								"Withdraw " + form.getCheck() + "from "
										+ customer.getLastname() + ","
										+ customer.getFirstname()
										+ "  successfully!");

					} catch (RollbackException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return "cm_requestcheck.jsp";
				} else {
					request.setAttribute(
							"errors",
							"Your current balance is not enough , please try later or decrease the amount !");
					return "cm_requestcheck.jsp";
				}
				
			} else {
				
				return "cm_requestcheck.jsp";
			}
		}
		
	}
}

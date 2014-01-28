//wc
package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.ReqcheckForm;
import pentagon.cfs.model.Model;

public class RequestCheck implements Action {

	private Model model;
	private TransactionDAO transactionDAO;

	public RequestCheck(Model model) {
		this.model = model;
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "requestcheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {

		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		

		if (customer == null) {
			return "login.jsp";
		} else {
			request.setAttribute("nav_cmreqcheck", "active");
			request.setAttribute("balance", (double) customer.getBalance() / 100);
			if ("submit".equals(request.getParameter("requestcheck_btn"))) {

				ReqcheckForm form = new ReqcheckForm(request);

				if (form.isComplete()) {

					Double amount = Double.parseDouble(request.getParameter("check"))*100;
					
					
					long check = amount.longValue() ;
					
					
					long balance =  customer.getBalance();
					if (check <= balance) {
						TransactionRecord record = new TransactionRecord();

						record.setCm_id(customer.getId());
						record.setAmount(check);
						record.setComplete(false);
						record.setType("withdraw");
						customer.setBalance(balance - check );

						try {
							transactionDAO.create(record);
							request.setAttribute("errors",
									" Withdraw " + form.getCheck()
											+ " from your account successfully!");
							
							model.getCustomerDAO().update(customer);
						
							
							
							return "cm_requestcheck.jsp";
						} catch (RollbackException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "cm_requestcheck.jsp";
						}
						
					} else {
						request.setAttribute(
								"errors",
								"Your current balance is not enough , please try later or decrease the amount !");
						return "cm_requestcheck.jsp";
					}

				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "cm_requestcheck.jsp";
				}
			} else {
				return "cm_requestcheck.jsp";
			}
		}
	}
}

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
				
				errors.addAll(form.checkErrors());
		        if (errors.size() != 0) {
		            return "cm_requestcheck.jsp";
		        }
				double check = form.getCheck();
				double cash = customer.getBalance();
				if (check <= cash) {
					TransactionRecord record = new TransactionRecord();
					
					
					record.setCm_id(customer.getId());
					record.setAmount((long) (check*100));
					record.setComplete(false);
					record.setType("withdraw");
                    customer.setBalance((long) (cash-check));

					
					try {
						transactionDAO.create(record);
						model.getCustomerDAO().update(customer);
						request.setAttribute("errors",
								"Withdraw " + form.getCheck() + "from your account successfully!");

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

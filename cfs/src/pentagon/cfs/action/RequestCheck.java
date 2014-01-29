/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
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
	public String perform(HttpServletRequest request) throws RollbackException {
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");

		if (customer == null) {
			return "login.jsp";
		}
		
		// refresh user
		CustomerDAO cmDAO = model.getCustomerDAO();
		customer = cmDAO.read(customer.getId());
		request.getSession().setAttribute("customer", customer);

		request.setAttribute("nav_cmreqcheck", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name", customer.getFirstname() + " "
				+ customer.getLastname());

		// set balance
		request.setAttribute("balance",
				String.format("%.2f", (double) customer.getBalance() / 100));

		if ("submit".equals(request.getParameter("requestcheck_btn"))) {
			ReqcheckForm form = new ReqcheckForm(request);
			if (form.isComplete()) {
				long amount = form.getAmount();
				long balance = customer.getBalance();
				if (amount <= balance) {
					// set Transaction
					TransactionRecord rd = new TransactionRecord();
					rd.setCm_id(customer.getId());
					rd.setType("withdraw");
					rd.setAmount(amount);
					rd.setComplete(false);
					transactionDAO.create(rd);

					// update balance
					customer.setBalance(balance - amount);
					cmDAO.update(customer);

					request.setAttribute(
							"op_success",
							String.format(
									"Transaction registered: Deposit $%.2f for user %s.",
									(double) amount / 100,
									customer.getUsername()));
					request.setAttribute(
							"balance",
							String.format("%.2f",
									(double) customer.getBalance() / 100));

					return "cm_requestcheck.jsp";
				} else {
					request.setAttribute("op_fail",
							"Your current balance is not enough!");
					return "cm_requestcheck.jsp";
				}
			} else {
				request.setAttribute("errors", form.getErrors());
				request.setAttribute("op_fail", "Invalid input!");
				return "cm_requestcheck.jsp";
			}
		} else {
			return "cm_requestcheck.jsp";
		}
	}

	@Override
	public String getName() {
		return "requestcheck.do";
	}
}

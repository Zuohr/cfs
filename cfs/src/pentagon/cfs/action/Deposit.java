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
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.DepositForm;
import pentagon.cfs.model.Model;

public class Deposit implements Action {
	private Model model;

	public Deposit(Model model) {
		this.model = model;
	}

	@Override
	public String getName() {
		return "deposit.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute(
				"employee");

		if (employee == null) {
			return "login.jsp";
		} else {
			if ("submit".equals(request.getParameter("deposit_btn"))) {
				DepositForm form = new DepositForm(request);
				request.setAttribute("nav_eeviewcmlist", "active");
				request.setAttribute("header_type", "Employee");
				request.setAttribute("header_name", employee.getFirstname()+" "+employee.getLastname());
				
				if (form.isComplete()) {
					String username = form.getUserName();
					CustomerDAO cmDAO = model.getCustomerDAO();
					Customer customer = cmDAO.getProfile(username);
					if (customer == null) {
						request.setAttribute("op_fail", "User does not exist!");
						return "ee_depositcheck.jsp";
					} else {
						TransactionDAO transDAO = model.getTransactionDAO();
						TransactionRecord rd = new TransactionRecord();
						rd.setAmount(form.getDepositAmount());
						rd.setCm_id(customer.getId());
						rd.setComplete(false);
						rd.setType("deposit");
						transDAO.create(rd);

						request.setAttribute(
								"op_success",
								String.format(
										"Transaction registered: Deposit $%.2f for user %s.",
										(double) form.getDepositAmount() / 100,
										customer.getUsername()));
						return "ee_depositcheck.jsp";
					}
				} else {
					request.setAttribute("errors", form.getErrors());
					request.setAttribute("op_fail", "Deposit check failed!");
					return "ee_depositcheck.jsp";
				}
			} else if ("cancel".equals(request.getParameter("deposit_btn"))) {
				return "emplviewcmlist.do";
			} else if (request.getParameter("usr") != null) {
				String username = request.getParameter("usr");
				request.setAttribute("username", username);
				return "ee_depositcheck.jsp";
			} else {
				return "ee_depositcheck.jsp";
			}
		}

	}
}
/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pentagon.cfs.model.Model;
import pentagon.cfs.dao.CustomerDAO;

import org.genericdao.RollbackException;

import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;

import pentagon.cfs.formbean.ResetpwForm;

public class ResetCmPw implements Action {
	private Model model;

	public ResetCmPw(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		if (employee == null) {
			return "login.jsp";
		} else {
			if ("submit".equals(request.getParameter("resetcmpw_btn"))) {
				ResetpwForm form = new ResetpwForm(request);

				if (form.isComplete()) {
					CustomerDAO customerDAO = model.getCustomerDAO();

					Customer customer = customerDAO.getProfile(form
							.getUserName());
					if (customer == null) {
						request.setAttribute("result",
								"Resetting password failed: user does not exist.");
					} else {
						request.setAttribute("FirstName",
								customer.getFirstname());
						request.setAttribute("LastName", customer.getLastname());

						customer.setPassword(form.getNewPassword());
						customerDAO.update(customer);

						request.setAttribute("result", String.format(
								"Password changed for %s.",
								customer.getUsername()));
					}
					return "ee_resetcmpw.jsp";

				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "ee_resetcmpw.jsp";
				}
			} else if ("cancel".equals(request.getParameter("resetcmpw_btn"))) {
				return "emplviewcmlist.do";
			} else if (request.getParameter("usr") != null) {
				String username = request.getParameter("usr");
				request.setAttribute("username", username);
				return "ee_resetcmpw.jsp";
			} else {
				return "emplviewcmlist.do";
			}
		}
	}

	@Override
	public String getName() {
		return "resetcmpw.do";
	}

}

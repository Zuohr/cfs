/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.formbean.ChangepwForm;
import pentagon.cfs.model.Model;

public class CmChangePw implements Action {

	private Model model;

	public CmChangePw(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		if (customer == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_cmchgpw", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name", customer.getFirstname() + " "
				+ customer.getLastname());

		if ("submit".equals(request.getParameter("cmchangepw_btn"))) {
			ChangepwForm form = new ChangepwForm(request);
			if (form.isComplete()) {
				// TODO
				String ret = "";
				try {
					Transaction.begin();
					// refresh user
					CustomerDAO customerDAO = model.getCustomerDAO();
					customer = customerDAO.read(customer.getId());
					request.getSession().setAttribute("customer", customer);

					if (customer.getPassword().equals(form.getOldPassword())) {
						customer.setPassword(form.getNewPassword());
						customerDAO.updateCustomer(customer);

						request.setAttribute("op_success",
								String.format("Password changed for %s %s.",
										customer.getFirstname(),
										customer.getLastname()));
						ret = "cm_changepw.jsp";
					} else {
						request.setAttribute("op_fail",
								"Old Password is wrong!");
						ret = "cm_changepw.jsp";
					}
					//TODO
					Transaction.commit();
				} catch (RollbackException e) {
					if (Transaction.isActive()) {
						Transaction.rollback();
					}
				}
				return ret;
			} else {
				request.setAttribute("errors", form.getErrors());
				request.setAttribute("op_fail", "Operation failed!");
				return "cm_changepw.jsp";
			}
		} else if ("cancel".equals(request.getParameter("cmchangepw_btn"))) {
			return "cmviewacct.do";
		} else {
			return "cm_changepw.jsp";
		}
	}

	@Override
	public String getName() {
		return "cmchangepw.do";
	}

}

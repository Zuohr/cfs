package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

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
		if ("submit".equals(request.getParameter("cmchangepw_btn"))) {
			ChangepwForm form = new ChangepwForm(request);
			if (form.isComplete()) {
				if (customer.getPassword().equals(form.getOldPassword())) {
					CustomerDAO customerDAO = model.getCustomerDAO();
					customer.setPassword(form.getNewPassword());
					customerDAO.updateCustomer(customer);

					request.setAttribute("result", String.format(
							"Password changed for %s %s.",
							customer.getFirstname(), customer.getLastname()));
					return "cm_changepw.jsp";
				} else {
					request.setAttribute("result", "Old Password is wrong!");
					return "cm_changepw.jsp";
				}
			} else {
				request.setAttribute("errors", form.getErrors());
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

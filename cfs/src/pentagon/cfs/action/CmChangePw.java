package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pentagon.cfs.model.Model;
import pentagon.cfs.dao.CustomerDAO;

import org.genericdao.RollbackException;

import pentagon.cfs.databean.Customer;
import pentagon.cfs.formbean.ChangepwForm;

public class CmChangePw implements Action {

	private Model model;

	public CmChangePw(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");

		if (customer == null) {
			return "login.do";
		} else {
			if ("submit".equals(request.getParameter("cmchangepw_btn"))) {
				ChangepwForm form = new ChangepwForm(request);

				if (form.isComplete()) {

					if (customer.getPassword() == form.getnewPassword()) {

						customer.setPassword(form.getnewPassword());
						customer.setId(customer.getId());

						CustomerDAO customerDAO = model.getCustomerDAO();

						customerDAO.updateCustomer(customer);

						request.setAttribute(
								"result",
								"Password changed for "
										+ customer.getFirstname() + " "
										+ customer.getLastname()
										+ " successfully!");
						return "cm_changepw.jsp";
					} else {
						request.setAttribute("result", "Your Old Password is wrong! ");
						return "cm_changepw.jsp";
					}

				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "cm_changepw.jsp";
				}
			} else if ("cancel".equals(request.getParameter("cancel_btn"))) {
				return "cmchangepw.do";
			} else {
				return "cm_changepw.jsp";
			}
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "cmchangepw.do";
	}

}

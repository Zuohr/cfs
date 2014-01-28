//shidong
package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.formbean.CmLoginForm;
import pentagon.cfs.model.Model;

public class CmLogin implements Action {
	private CustomerDAO customerDAO;

	public CmLogin(Model model) {
		this.customerDAO = model.getCustomerDAO();
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		String role = request.getParameter("submit");
		if (role == null) {
			return "login.jsp";
		} else if (role.startsWith("cu")) {
			CmLoginForm form = new CmLoginForm(request);
			if (form.isComplete()) {
				if (customerDAO.getProfile(form.getUserName()) != null) {
					Customer cm = customerDAO.getProfile(form.getUserName());
					if (cm.getPassword().equals(form.getPassword())) {
						request.getSession().setAttribute("customer", cm);
						return "cmviewacct.do";
					} else {
						request.setAttribute("result", "Password not correct");
						return "login.jsp";
					}
				} else {
					request.setAttribute("result", "Non-exist user");
					return "login.jsp";
				}
			} else {
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
				System.out.println(errors);
				return "login.jsp";
			}
		} else {
			return "login.jsp";
		}

	}

	@Override
	public String getName() {
		return "cmlogin.do";
	}

}

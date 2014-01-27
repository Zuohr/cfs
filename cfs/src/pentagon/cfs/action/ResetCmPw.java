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

					String usr = request.getParameter("username");
					System.out.println(usr);
					Customer customer = customerDAO.getProfile(usr);

					request.setAttribute("FirstName", customer.getFirstname());
					request.setAttribute("LastName", customer.getLastname());

					customer.setPassword(form.getnewPassword());

					customerDAO.update(customer);

					request.setAttribute("result", String.format(
							"Password changed for %s %s.",
							customer.getFirstname(), customer.getLastname()));
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
				return "emplviewcmlist.do";
			} else {
				return "ee_resetcmpw.jsp";
			}
		}
	}

	@Override
	public String getName() {
		return "resetcmpw.do";
	}

}

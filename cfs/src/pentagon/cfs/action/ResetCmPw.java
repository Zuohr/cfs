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
			return "login.do";
		} else {
			if ("submit".equals(request.getParameter("resetcmpw_btn"))) {
				ResetpwForm form = new ResetpwForm(request);

				if (form.isComplete()) {
					CustomerDAO customerDAO = model.getCustomerDAO();

					int id = Integer.parseInt(request.getParameter("id"));
					Customer customer = customerDAO.read(id);

					request.setAttribute("FirstName", customer.getFirstname());
					request.setAttribute("LastName", customer.getLastname());

					customer.setPassword(form.getnewPassword());
					customer.setId(id);

					customerDAO.updateCustomer(customer);

					request.setAttribute("result",
							"Password changed for " + customer.getFirstname()
									+ "," + customer.getLastname()
									+ " successfully!");
					return "ee_resetcmpw.jsp";

				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "ee_resetcmpw.jsp";
				}
			} else if ("cancel".equals(request.getParameter("cancel_btn"))) {
				return "resetcmpw.do";
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

package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

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

		List<String> errors = new ArrayList<String>();
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");

		if (customer == null) {
			return "login.do";
		} else {
			if ("submit".equals(request.getParameter("cmchangepw_btn"))) {
				ChangepwForm form = new ChangepwForm(request);
				System.out.print("0");
				if (!customer.getPassword().equals(form.getnewPassword())) {
					errors.add("Old Password is Not Correct!");
					System.out.print("1");
					return "cm_changepw.jsp";
				} else {
					String newPassword = form.getnewPassword();
					System.out.print("2");
					customer.setPassword(newPassword);
					customer.setId(customer.getId());
					try {
						// Change the password
						CustomerDAO cmDAO = model.getCustomerDAO();
						cmDAO.updateCustomer(customer);

						request.setAttribute(
								"errors",
								"Password changed for "
										+ customer.getFirstname() + " "
										+ customer.getLastname());
						return "cm_changepw.jsp";
					} catch (RollbackException e) {
						errors.add(e.toString());
						return "cm_changepw.jsp";
					}
				}
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

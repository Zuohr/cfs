package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.List;
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
		List<String> errors = new ArrayList<String>();
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		ChangepwForm form = new ChangepwForm(request);

		if (customer == null) {
			return "login.do";
		}else{
		if ("submit".equals(request.getParameter("cmchangepw_btn"))) {
			if (!customer.getPassword().equals(form.getoldPassword())) {
				errors.add("Old Password is Not Correct!");
				return "cm_changepw.jsp";
			}else{
			String newPassword = form.getnewPassword();
			customer.setPassword(newPassword);
			try {
				// Change the password
				CustomerDAO cmDAO = model.getCustomerDAO();
				cmDAO.updateCustomer(customer);

				request.setAttribute("errors",
						"Password changed for " + customer.getFirstname() + " "
								+ customer.getLastname());
				return "cm_changepw.jsp";
			} catch (RollbackException e) {
				errors.add(e.toString());
				return "cm_changepw.jsp";
			}
			}
		}
		}
		return "cm_changepw.jsp";

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}

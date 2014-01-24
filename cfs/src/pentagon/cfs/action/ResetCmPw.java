package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pentagon.cfs.model.Model;
import pentagon.cfs.dao.CustomerDAO;

import org.genericdao.RollbackException;

import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.ResetpwForm;


import org.genericdao.RollbackException;

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.formbean.CreateFundForm;
import pentagon.cfs.model.Model;




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
			return "login.do"; // employee login page
		} else {
			if ("submit".equals(request.getParameter("resetcmpw_btn"))) {
				ResetpwForm form = new ResetpwForm(request);
				
				if (form.isComplete()) {
					CustomerDAO CustomerDAO = model.getCustomerDAO();
					////////////
					Fund newFund = form.getFund();

					if (dao.createFund(newFund)) {
						request.setAttribute(
								"result",
								"Fund: " + newFund.getName() + "("
										+ newFund.getSymbol() + ") created.");
					} else {
						request.setAttribute("result",
								"Fund creation failed, fund already exist.");
					}
					return "ee_createfund.jsp";
				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "ee_createfund.jsp";
				}
			} else {
				return "empl_main.jsp";
			}
		}
	}

	@Override
	public String getName() {
		return "createfund.do";
	}

}

















///////////////////////////////////////////

public class ResetCmPw implements Action {

	private CustomerDAO CustomerDAO;

	public ResetCmPw(Model model) {
		CustomerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "resetcmpw.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
	    int id = (Integer) request.getAttribute("id");
	    Employee employee = (Employee) request.getSession().getAttribute("employee");

		//
		if (employee == null) {
			return "cmlogin.do";
		} else {

			ResetpwForm form = new ResetpwForm(request);
			Customer customer = new Customer();

			String newPW = form.getNewPw();
			customer.setPassword(newPW);
            customer.setId(id);
			try {
				// Change the password
				CustomerDAO.updateCustomer(customer);

				request.setAttribute("errors", "Reser Password for "
						+ customer.getFirstname() + " " + customer.getLastname());
				return "ee_resetcmpw.jsp";
			} catch (RollbackException e) {
				errors.add(e.toString());
				return "ee_resetcmpw.jsp";
			}

		}
	}
}

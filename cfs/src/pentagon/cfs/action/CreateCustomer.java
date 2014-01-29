/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.formbean.CreateCmForm;
import pentagon.cfs.model.Model;

public class CreateCustomer implements Action {

	private CustomerDAO customerDAO;
	private Model model;

	public CreateCustomer(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if(employee == null) {
			return "login.jsp";
		}
		
		request.setAttribute("nav_eecreatecm", "active");
		request.setAttribute("header_type", "Employee");
		request.setAttribute("header_name", employee.getFirstname()+" "+employee.getLastname());
		if ("submit".equals(request.getParameter("createcm_btn"))) {
			CreateCmForm form = new CreateCmForm(request);
			if (form.isComplete()) {
				Customer newCustomer = form.getCustomerBean();
				customerDAO = model.getCustomerDAO();
				if (customerDAO.createCustomer(newCustomer)) {
					request.setAttribute("op_success",
							"Customer: " + newCustomer.getUsername() + " created.");
				} else {
					request.setAttribute("result",
							"Customer creation failed, customer already exist.");
				}
				return "ee_createcm.jsp";
			} else {
				request.setAttribute("errors", form.getErrors());
				request.setAttribute("op_fail", "New customer create failed!");
				return "ee_createcm.jsp";
			}
		} else if ("cancel".equals(request.getParameter("createcm_btn"))) {
			return "emplviewcmlist.do";
		} else {
			return "ee_createcm.jsp";
		}
	}

	@Override
	public String getName() {
		return "createcm.do";
	}

}

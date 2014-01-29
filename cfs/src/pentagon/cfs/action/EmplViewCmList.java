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
import pentagon.cfs.model.Model;

public class EmplViewCmList implements Action {
	private Model model;

	public EmplViewCmList(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee user = (Employee) request.getSession().getAttribute("employee");
		if (user == null) {
			return "login.jsp";
		}
		
		request.setAttribute("nav_eeviewcmlist", "active");
		request.setAttribute("header_type", "Employee");
		request.setAttribute("header_name",
				user.getFirstname() + " " + user.getLastname());

		CustomerDAO cusDAO = model.getCustomerDAO();
		Customer[] cus_list = cusDAO.match();
		request.setAttribute("customer_list", cus_list);
		return "ee_viewcmlist.jsp";
	}

	@Override
	public String getName() {
		return "emplviewcmlist.do";
	}

}

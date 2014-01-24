package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;
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
		
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if (employee == null) {
			return "login.do"; // employee login page
		} else {
			if ("submit".equals(request.getParameter("resetcmpw_btn"))) {
				
				ResetpwForm form = new ResetpwForm(request);
				
				if (form.isComplete()) {
					CustomerDAO customerDAO = model.getCustomerDAO();
					Customer customer= new Customer();
				    int id =Integer.parseInt( request.getParameter("id"));
				    
				    customer.setId(id);
				    customer.setPassword(form.getNewPw());
				    
				    customerDAO.update(customer);
				    
				    request.setAttribute("errors",
							"Change password successfully!");
				    return "ee_resetcmpw.jsp";
						
					} else {
					return "ee_resetcmpw.jsp";
					}
				 
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


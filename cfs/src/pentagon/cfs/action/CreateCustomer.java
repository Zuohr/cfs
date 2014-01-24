//shidong
package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.formbean.CreateCmForm;
import pentagon.cfs.model.Model;

public class CreateCustomer implements Action {

	private CustomerDAO customerDAO;

	public CreateCustomer(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		System.out.println(request.getParameter("createcm_btn"));
		if ("submit".equals(request.getParameter("createcm_btn"))) {
			CreateCmForm form = new CreateCmForm(request);
			if (form.isComplete()) {
				System.out.println("3");
				Customer newCm = form.getCustomerBean();
				if (customerDAO.createCustomer(newCm)) {
					request.setAttribute("result",
							"Customer:" + newCm.getUsername() + "created.");
				} else {
					request.setAttribute("result",
							"Customer creation failed, customer already exist.");
				}
				return "ee_createcm.jsp";
			} else {
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
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

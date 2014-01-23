/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.formbean.CreateFundForm;
import pentagon.cfs.model.Model;

public class CreateFund implements Action {
	private Model model;

	public CreateFund(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Employee user = (Employee) session.getAttribute("user");
		if (user == null) {
			return "login.do";
		} else {
			if ("submit".equals(request.getParameter("createfund_btn"))) {
				CreateFundForm form = new CreateFundForm(request);
				if (form.isComplete()) {
					FundDAO dao = model.getFundDAO();
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

// hzuo

package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.formbean.TransitionForm;
import pentagon.cfs.model.Model;

public class TransitionDay implements Action {
	private Model model;

	public TransitionDay(Model model) {
		super();
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee empl = (Employee) request.getSession().getAttribute("user");
		if (empl == null) {
			return "login.do";
		} else {
			FundDAO dao = model.getFundDAO();
			Fund[] fund_list = dao.match();
			request.setAttribute("fund_list", fund_list);
			request.setAttribute("fund_num", fund_list.length);
			request.setAttribute("last_day", Meta.lastDate);
			if ("submit".equals(request.getParameter("btn_transition"))) {
				TransitionForm form = new TransitionForm(request);
				if (form.isComplete()) {
					request.setAttribute("result", "bingo");
					return "ee_transition.jsp";
				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "ee_transition.jsp";
				}
			} else if ("cancel".equals(request.getParameter("btn_transition"))) {
				return "empl_main.jsp";
			} else {
				return "ee_transition.jsp";
			}
		}
	}

	@Override
	public String getName() {
		return "transition.do";
	}

}

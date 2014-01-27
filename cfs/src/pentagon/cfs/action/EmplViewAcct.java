/*Hao Ge
 * 
 * 
 * */
package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Position;
import pentagon.cfs.model.Model;

public class EmplViewAcct implements Action {
	private Model model;

	public EmplViewAcct(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Employee user = (Employee) session.getAttribute("employee");
		if (user == null) {
			return "login.do";
		} else {
			CustomerDAO cusDAO = model.getCustomerDAO();
			Customer cus = cusDAO.getProfile(request.getParameter("usr"));
			PositionDAO posDAO = model.getPositionDAO();
			Position[] pos = posDAO.getPositions(cus.getId());
			FundDAO fundDAO = model.getFundDAO();
			PositionList[] plist = new PositionList[pos.length];
			for (int i = 0; i < pos.length; i++) {
				plist[i] = new PositionList(fundDAO.read(pos[i].getFund_id())
						.getName(), pos[i].getShare() / 1000);
			}
			request.setAttribute("view_customer", cus);
			request.setAttribute("cus_position", plist);

			return "cm_viewacct.jsp";
		}
	}

	@Override
	public String getName() {
		return "emplviewacct.do";
	}

	public class PositionList {
		private String fundName;
		private double share;

		public PositionList(String fundName, double share) {
			this.fundName = fundName;
			this.share = share;
		}
	}

}

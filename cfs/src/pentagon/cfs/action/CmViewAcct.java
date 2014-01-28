/*Hao Ge
 * 
 * 
 * */

package pentagon.cfs.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.Position;
import pentagon.cfs.model.Model;

public class CmViewAcct implements Action {
	private Model model;

	public CmViewAcct(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("customer");
		if (user == null) {
			return "login.jsp";
		} else {
			request.setAttribute("nav_cmviewacct", "active");
			
			double cash = (double) user.getCash() / 100;
			request.setAttribute("cash", String.format("%.2f", cash));
			
			if (user.getLasttrading() == null) {
				request.setAttribute("lastTradingDay", "-");
			} else {
				request.setAttribute("lastTradingDay",
						new SimpleDateFormat(Meta.DATE_FORMAT)
								.format(user.getLasttrading()));
			}
			
			PositionDAO posDAO = model.getPositionDAO();
			Position[] pos = posDAO.getPositions(user.getId());

			FundDAO fundDAO = model.getFundDAO();
			PositionRecord[] plist = new PositionRecord[pos.length];
			for (int i = 0; i < pos.length; i++) {
				plist[i] = new PositionRecord(fundDAO.read(pos[i].getFund_id())
						.getName(), pos[i].getShare() / 1000);
			}
			request.setAttribute("view_customer", user);
			request.setAttribute("cus_position", plist);

			return "cm_viewacct.jsp";
		}
	}

	@Override
	public String getName() {
		return "cmviewacct.do";
	}

	public class PositionRecord {
		private String fundName;
		private double share;

		public PositionRecord(String fundName, double share) {
			this.fundName = fundName;
			this.share = share;
		}

		public String getFundName() {
			return fundName;
		}

		public double getShare() {
			return share;
		}
	}
}

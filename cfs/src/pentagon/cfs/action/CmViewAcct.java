/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
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
		Customer user = (Customer) request.getSession()
				.getAttribute("customer");
		if (user == null) {
			return "login.jsp";
		}
		
		// refresh customer
		CustomerDAO cmDAO = model.getCustomerDAO();
		user = cmDAO.read(user.getId());
		request.getSession().setAttribute("customer", user);
		
		request.setAttribute("nav_cmviewacct", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name",
				user.getFirstname() + " " + user.getLastname());
		double cash = (double) user.getCash() / 100;
		request.setAttribute("cash", String.format("%.2f", cash));

		if (user.getLasttrading() == null) {
			request.setAttribute("lastTradingDay", "-");
		} else {
			request.setAttribute("lastTradingDay", new SimpleDateFormat(
					Meta.DATE_FORMAT).format(user.getLasttrading()));
		}

		PositionDAO posDAO = model.getPositionDAO();
		Position[] pos = posDAO.getPositions(user.getId());

		FundDAO fundDAO = model.getFundDAO();
		PositionRecord[] plist = new PositionRecord[pos.length];
		for (int i = 0; i < pos.length; i++) {
			plist[i] = new PositionRecord(fundDAO.read(pos[i].getFund_id())
					.getName(), pos[i].getShare(), pos[i].getSharebalance());
		}
		request.setAttribute("view_customer", user);
		request.setAttribute("cus_position", plist);

		return "cm_viewacct.jsp";
	}

	@Override
	public String getName() {
		return "cmviewacct.do";
	}

	public class PositionRecord {
		private String fundName;
		private String share;
		private String shareBalance;

		public PositionRecord(String fundName, long share, long shareBalance) {
			this.fundName = fundName;
			this.share = String.format("%.3f", (double) share / 1000);
			this.shareBalance = String.format("%.3f",
					(double) shareBalance / 1000);
		}

		public String getFundName() {
			return fundName;
		}

		public String getShare() {
			return share;
		}

		public String getShareBalance() {
			return shareBalance;
		}
	}
}

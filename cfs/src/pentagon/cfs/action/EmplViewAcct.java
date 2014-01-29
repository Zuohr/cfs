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
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.Position;
import pentagon.cfs.databean.PositionRecord;
import pentagon.cfs.model.CommonUtil;
import pentagon.cfs.model.Model;

public class EmplViewAcct implements Action {
	private Model model;

	public EmplViewAcct(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee user = (Employee) request.getSession()
				.getAttribute("employee");
		if (user == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_eeviewcmlist", "active");

		request.setAttribute("header_type", "Employee");
		request.setAttribute("header_name",
				user.getFirstname() + " " + user.getLastname());

		String username = request.getParameter("usr");
		if (username == null || username.isEmpty()) {
			return "emplviewcmlist.do";
		}
		
		CustomerDAO cmDAO = model.getCustomerDAO();
		Customer customer = cmDAO.getProfile(username);
		if (customer == null) {
			return "emplviewcmlist.do";
		}
		
		double cash = (double) customer.getCash() / 100;
		double balance = (double) customer.getBalance() / 100;
		request.setAttribute("cash", String.format("%.2f", cash));
		request.setAttribute("balance", String.format("%.2f", balance));

		if (customer.getLasttrading() == null) {
			request.setAttribute("lastTradingDay", "-");
		} else {
			request.setAttribute("lastTradingDay", new SimpleDateFormat(
					Meta.DATE_FORMAT).format(customer.getLasttrading()));
		}
		request.setAttribute("view_customer", customer);

		PositionDAO posDAO = model.getPositionDAO();
		Position[] pos = posDAO.getPositions(user.getId());
		FundDAO fundDAO = model.getFundDAO();
		FundPriceHistoryDAO fphDAO = model.getFundPriceHistoryDAO();
		PositionRecord[] plist = new PositionRecord[pos.length];
		for (int i = 0; i < pos.length; i++) {
			Fund fund = fundDAO.read(pos[i].getFund_id());
			FundPriceHistory[] fphs = fphDAO.getHistory(fund.getId());
			long price = 0;
			if (fphs.length > 0) {
				price = fphs[fphs.length - 1].getPrice();
			}
			plist[i] = new PositionRecord(CommonUtil.getResearchURL(fund), pos[i].getShare(),
					pos[i].getSharebalance(), price);
		}
		request.setAttribute("cus_position", plist);

		return "ee_viewcmacct.jsp";
	}

	@Override
	public String getName() {
		return "emplviewacct.do";
	}

}

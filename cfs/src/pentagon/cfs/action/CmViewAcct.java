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
import org.genericdao.Transaction;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.Position;
import pentagon.cfs.databean.PositionRecord;
import pentagon.cfs.model.CommonUtil;
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

		// TODO
		try {
			Transaction.begin();
			// refresh customer
			CustomerDAO cmDAO = model.getCustomerDAO();
			user = cmDAO.read(user.getId());
			request.getSession().setAttribute("customer", user);

			request.setAttribute("nav_cmviewacct", "active");
			request.setAttribute("header_type", "Customer");
			request.setAttribute("header_name", user.getFirstname() + " "
					+ user.getLastname());

			double cash = (double) user.getCash() / 100;
			double balance = (double) user.getBalance() / 100;
			request.setAttribute("cash", String.format("%.2f", cash));
			request.setAttribute("balance", String.format("%.2f", balance));

			if (user.getLasttrading() == null) {
				request.setAttribute("lastTradingDay", "-");
			} else {
				request.setAttribute("lastTradingDay", new SimpleDateFormat(
						Meta.DATE_FORMAT).format(user.getLasttrading()));
			}

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
				plist[i] = new PositionRecord(CommonUtil.getResearchURL(fund),
						pos[i].getShare(), pos[i].getSharebalance(), price);
			}

			request.setAttribute("view_customer", user);
			request.setAttribute("cus_position", plist);
			// TODO
			Transaction.commit();
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
		return "cm_viewacct.jsp";
	}

	@Override
	public String getName() {
		return "cmviewacct.do";
	}

}

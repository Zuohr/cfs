/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

// hzuo
package pentagon.cfs.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.model.Model;

public class ResearchFund implements Action {
	private Model model;

	public ResearchFund(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Object user = request.getSession().getAttribute("customer");
		if (user == null) {
			user = request.getSession().getAttribute("employee");
		}
		if (user == null) {
			return "login.jsp";
		} else {
			// verify input
			String input = request.getParameter("fund_id");
			if (input == null
					|| !input.matches("\\d+")
					|| input.length() > 10
					|| (input.length() == 10 && input.compareTo(String
							.valueOf(Integer.MAX_VALUE)) > 0)) {
				return "404";
			}

			// check if fund exists
			Integer fund_id = Integer.parseInt(request.getParameter("fund_id"));
			FundDAO fundDAO = model.getFundDAO();
			Fund fund = fundDAO.read(fund_id);
			if (fund != null) {
				request.setAttribute("fund", fund);
				FundPriceHistoryDAO dao = model.getFundPriceHistoryDAO();
				FundPriceHistory[] history = dao.getHistory(fund_id);
				Record[] records = new Record[history.length];
				for (int i = 0; i < records.length; i++) {
					records[i] = new Record(history[i]);
				}
				request.setAttribute("records", records);
				return "researchfund.jsp";
			} else {
				return "404";
			}
		}
	}

	@Override
	public String getName() {
		return "researchfund.do";
	}

	public class Record {
		String date;
		String price;

		public Record(FundPriceHistory rd) {
			SimpleDateFormat sdf = new SimpleDateFormat(Meta.DATE_FORMAT);
			this.date = sdf.format(rd.getDate());
			this.price = String.format("%.2f", (double) rd.getPrice() / 100);
		}

		public String getDate() {
			return date;
		}

		public String getPrice() {
			return price;
		}
	}
}

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

import pentagon.cfs.dao.FundPriceHistoryDAO;
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
		Object user = request.getSession().getAttribute("user");
		if (user == null) {
			return "login.do";
		} else {
			String input = request.getParameter("fund_id");
			if (input == null || !input.matches("\\d+")) {
				return "404";
			} else {
				Integer fund_id = Integer.parseInt(request
						.getParameter("fund_id"));
				FundPriceHistoryDAO dao = model.getFundPriceHistoryDAO();
				FundPriceHistory[] history = dao.getHistory(fund_id);
				Record[] records = new Record[history.length];
				for(int i = 0; i < records.length; i++) {
					records[i] = new Record(history[i]);
				}
				request.setAttribute("records",	records);
				return "researchfund.jsp";
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
			this.price = String.valueOf((double) rd.getPrice() / 100);
		}

		public String getDate() {
			return date;
		}

		public String getPrice() {
			return price;
		}
	}
}

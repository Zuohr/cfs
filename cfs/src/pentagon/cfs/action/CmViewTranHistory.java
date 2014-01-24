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
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.model.Model;

public class CmViewTranHistory implements Action {
	private Model model;

	public CmViewTranHistory(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Customer user = (Customer) request.getSession().getAttribute("customer");
		if (user == null) {
			return "login.do";
		} else {
			int cm_id = user.getId();
			TransactionDAO dao = model.getTransactionDAO();
			TransactionRecord[] transactions = dao.getHistory(cm_id);
			Record[] records = new Record[transactions.length];
			for (int i = 0; i < records.length; i++) {
				records[i] = new Record(transactions[i]);
			}
			request.setAttribute("records", records);
			return "cm_history.jsp";
		}
	}

	@Override
	public String getName() {
		return "cmviewtranhistory.do";
	}

	public class Record {
		private String date;
		private String type;
		private String fundname;
		private String share;
		private String price;
		private String dollar;
		private String state;
		private String url;

		public Record(TransactionRecord rd) {
			SimpleDateFormat sdf = new SimpleDateFormat(Meta.DATE_FORMAT);
			this.date = sdf.format(rd.getDate());
			this.type = rd.getType();
			FundDAO fundDAO = model.getFundDAO();
			try {
				if (rd.getFund_id() != 0) {
					Fund fund = fundDAO.read(Integer.valueOf(rd.getFund_id()));
					this.fundname = String.format(
							"<a href=\"researchfund.do?fund_id=%s\">%s(%s)</a>",
							String.valueOf(rd.getFund_id()), fund.getName(), fund.getSymbol());
					this.share = String.valueOf(100);
					this.price = String.valueOf(0.1);
				} else {
					this.fundname = "-";
					this.share = "-";
					this.price = "-";
				}
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.dollar = String.valueOf(50);
			this.state = rd.isComplete() ? "completed" : "pending";
		}

		public String getDate() {
			return date;
		}

		public String getType() {
			return type;
		}

		public String getFundname() {
			return fundname;
		}

		public String getShare() {
			return share;
		}

		public String getPrice() {
			return price;
		}

		public String getDollar() {
			return dollar;
		}

		public String getState() {
			return state;
		}

		public String getUrl() {
			return url;
		}
	}

}

package pentagon.cfs.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.model.Model;

public class EmplViewTranHistroy implements Action {

	private Model model;

	public EmplViewTranHistroy(Model model) {

		this.model = model;
	}

	@Override
	public String getName() {
		return "emplviewtranhistroy.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute(
				"employee");

		if (employee == null) {
			return "login.jsp";
		} else {
			String username = request.getParameter("usr");
			if (username == null) {
				return "emplviewcmlist.do";
			} else {
				CustomerDAO cmDAO = model.getCustomerDAO();
				Customer customer = cmDAO.getProfile(username);
				if (customer == null) {
					return "emplviewcmlist.do";
				} else {
					TransactionDAO dao = model.getTransactionDAO();
					TransactionRecord[] transactions = dao.getHistory(customer
							.getId());
					Record[] records = new Record[transactions.length];
					for (int i = 0; i < records.length; i++) {
						records[i] = new Record(transactions[i]);
					}
					request.setAttribute("records", records);
					request.setAttribute("customer", customer);
					return "ee_cmhistory.jsp";
				}
			}
		}
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
					this.fundname = String
							.format("<a href=\"researchfund.do?fund_id=%s\">%s(%s)</a>",
									String.valueOf(rd.getFund_id()),
									fund.getName(), fund.getSymbol());
					this.share = String.valueOf((double) rd.getShare() / 1000);

					if (rd.isComplete()) {
						FundPriceHistoryDAO priceDAO = model
								.getFundPriceHistoryDAO();
						FundPriceHistory[] fph = priceDAO
								.match(MatchArg.and(MatchArg.equals("fund_id",
										Integer.valueOf(rd.getFund_id())),
										MatchArg.equals("date", rd.getDate())));
						long priceLong = fph[0].getPrice();
						this.price = String.valueOf((double) priceLong / 100);
					} else {
						this.price = "-";
					}

				} else {
					this.fundname = "-";
					this.share = "-";
					this.price = "-";
				}
			} catch (RollbackException e) {
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

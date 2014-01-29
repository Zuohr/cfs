/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

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
import pentagon.cfs.model.CommonUtil;
import pentagon.cfs.model.Model;

public class EmplViewTranHistroy implements Action {

	private Model model;

	public EmplViewTranHistroy(Model model) {
		this.model = model;
	}

	@Override
	public synchronized String perform(HttpServletRequest request)
			throws RollbackException {
		Employee employee = (Employee) request.getSession().getAttribute(
				"employee");

		if (employee == null) {
			return "login.jsp";
		} else {
			request.setAttribute("nav_eeviewcmlist", "active");
			request.setAttribute("header_type", "Employee");
			request.setAttribute("header_name", employee.getFirstname() + " "
					+ employee.getLastname());
			String username = request.getParameter("usr");
			if (username == null) {
				return "emplviewcmlist.do";
			}

			CustomerDAO cmDAO = model.getCustomerDAO();
			Customer customer = cmDAO.getProfile(username);
			if (customer == null) {
				return "emplviewcmlist.do";
			}
			
			TransactionDAO dao = model.getTransactionDAO();
			TransactionRecord[] transactions = dao.getHistory(customer.getId());
			Record[] records = new Record[transactions.length];
			for (int i = 0; i < records.length; i++) {
				records[i] = new Record(transactions[i]);
			}
			request.setAttribute("records", records);
			request.setAttribute("customer_tran", customer);
			return "ee_cmhistory.jsp";
		}
	}

	@Override
	public String getName() {
		return "emplviewtranhistroy.do";
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

		public Record(TransactionRecord rd) throws RollbackException {
			// set date
			if (rd.isComplete()) {
				SimpleDateFormat sdf = new SimpleDateFormat(Meta.DATE_FORMAT);
				this.date = sdf.format(rd.getDate());
				this.state = "complete";
			} else {
				this.date = "-";
				this.state = "pending";
			}

			// set type
			this.type = rd.getType();

			// set fund name, share, price, amount
			if ("deposit".equals(this.type) || "withdraw".equals(this.type)) {
				this.fundname = "-";
				this.share = "-";
				this.price = "-";
				this.dollar = String.format("%.2f",
						(double) rd.getAmount() / 100);
			} else {
				FundDAO fundDAO = model.getFundDAO();
				Fund fund = fundDAO.read(Integer.valueOf(rd.getFund_id()));
				this.fundname = CommonUtil.getResearchURL(fund);

				FundPriceHistoryDAO fphDAO = model.getFundPriceHistoryDAO();
				FundPriceHistory[] fphs = fphDAO.match(MatchArg.and(
						MatchArg.equals("fund_id",
								Integer.valueOf(fund.getId())),
						MatchArg.equals("date", rd.getDate())));

				if ("buy".equals(this.type)) {
					this.dollar = String.format("%.2f",
							(double) rd.getAmount() / 100);
					if (rd.isComplete()) {
						this.share = String.format("%.3f",
								(double) rd.getShare() / 1000);
						this.price = String.format("%.2f",
								(double) fphs[0].getPrice() / 100);
					} else {
						this.share = "-";
						this.price = "-";
					}
				} else {
					this.share = String.format("%.3f",
							(double) rd.getShare() / 1000);
					if (rd.isComplete()) {
						this.dollar = String.format("%.2f",
								(double) rd.getAmount() / 100);
						this.price = String.format("%.2f",
								(double) fphs[0].getPrice() / 100);
					} else {
						this.dollar = "-";
						this.price = "-";
					}
				}
			}

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

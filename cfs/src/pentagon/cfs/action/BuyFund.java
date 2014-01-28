/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.BuyForm;
import pentagon.cfs.model.Model;

public class BuyFund implements Action {
	private Model model;

	public BuyFund(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Customer user = (Customer) request.getSession()
				.getAttribute("customer");
		if (user == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_cmbuyfund", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name", user.getFirstname()+" "+user.getLastname());
		FundDAO fundDAO = model.getFundDAO();
		Fund[] fund_list = fundDAO.match();
		FundPriceHistoryDAO fphDAO = model.getFundPriceHistoryDAO();

		// set fund list
		BuyFundList[] bflist = new BuyFundList[fund_list.length];
		for (int i = 0; i < fund_list.length; i++) {
			FundPriceHistory[] fph = fphDAO.getHistory(fund_list[i].getId());
			double bf_price = 0;
			if (fph.length != 0) {
				bf_price = (double) fph[fph.length - 1].getPrice() / 100;
			}
			bflist[i] = new BuyFundList(fund_list[i].getName(),
					fund_list[i].getSymbol(), fund_list[i].getId(), bf_price);
		}
		request.setAttribute("bf_list", bflist);

		CustomerDAO cmDAO = model.getCustomerDAO();

		double currBalance = (double) user.getBalance() / 100;

		request.setAttribute("ava_bal", String.format("%.2f", currBalance));

		if ("submit".equals(request.getParameter("buyfund_btn"))) {
			BuyForm form = new BuyForm(request);

			if (form.isComplete()) {
				// check if fund exists
				Fund fund = fundDAO.read(form.getFund_id());
				if (fund == null) {
					return "cm_buyfund.jsp";
				}

				// check if balance is enough
				if (form.getAmount() > user.getBalance()) {
					request.setAttribute("op_fail",
							"You do not have enough balance");
					return "cm_buyfund.jsp";
				}

				// set transaction
				TransactionDAO transDao = model.getTransactionDAO();
				TransactionRecord rd = new TransactionRecord();
				rd.setCm_id(user.getId());
				rd.setType("buy");
				rd.setAmount(form.getAmount());
				rd.setFund_id(fund.getId());
				rd.setComplete(false);
				transDao.create(rd);
				request.setAttribute("op_success",
						"You have successfully placed the order");

				// update balance
				user.setBalance(user.getBalance() - form.getAmount());
				cmDAO.update(user);

				// update available balance
				currBalance = (double) user.getBalance() / 100;
				request.setAttribute("ava_bal",
						String.format("%.2f", currBalance));

				return "cm_buyfund.jsp";

				// // update
				// fund_list = fundDAO.match();
				//
				// bflist = new BuyFundList[fund_list.length];
				// for (int i = 0; i < fund_list.length; i++) {
				// FundPriceHistory[] fph = fphDAO.getHistory(fund_list[i]
				// .getId());
				// double bf_price = 0;
				// if (fph.length != 0) {
				// bf_price = (double) fph[fph.length - 1].getPrice() / 100;
				// }
				// bflist[i] = new BuyFundList(fund_list[i].getName(),
				// fund_list[i].getSymbol(), fund_list[i].getId(),
				// bf_price);
				// }
				// request.setAttribute("bf_list", bflist);
				// request.setAttribute("ava_bal", currBalance);

			} else {
				request.setAttribute("errors", form.getErrors());
				return "cm_buyfund.jsp";
			}
		} else {
			return "cm_buyfund.jsp";
		}
	}

	@Override
	public String getName() {
		return "buyfund.do";
	}

	public class BuyFundList {
		private String fundName;
		private String ticker;
		private int fundId;
		private double price;

		public BuyFundList(String fundName, String ticker, int fundId,
				double price) {
			this.fundName = fundName;
			this.ticker = ticker;
			this.fundId = fundId;
			this.price = price;
		}

		public String getFundName() {
			return fundName;
		}

		public String getTicker() {
			return ticker;
		}

		public int getFundId() {
			return fundId;
		}

		public double getPrice() {
			return price;
		}
	}
}

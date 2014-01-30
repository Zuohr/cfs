/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.PositionRecord;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.BuyForm;
import pentagon.cfs.model.CommonUtil;
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

		// refresh user
		CustomerDAO cmDAO = model.getCustomerDAO();
		user = cmDAO.read(user.getId());
		request.setAttribute("customer", user);

		request.setAttribute("nav_cmbuyfund", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name",
				user.getFirstname() + " " + user.getLastname());

		FundDAO fundDAO = model.getFundDAO();
		// TODO
		try {
			Transaction.begin();

			// set fund list
			Fund[] fund_list = fundDAO.match();
			FundPriceHistoryDAO fphDAO = model.getFundPriceHistoryDAO();
			PositionRecord[] prlist = new PositionRecord[fund_list.length];
			for (int i = 0; i < fund_list.length; i++) {
				FundPriceHistory[] fph = fphDAO
						.getHistory(fund_list[i].getId());
				long price = 0;
				if (fph.length != 0) {
					price = fph[fph.length - 1].getPrice();
				}
				prlist[i] = new PositionRecord(
						CommonUtil.getResearchURL(fund_list[i]), 0, 0, price);
				prlist[i].setFundId(fund_list[i].getId());
				if (price == 0) {
					prlist[i].setLastPrice("-");
					prlist[i].setLastPrice("-");
				}
			}
			request.setAttribute("pr_list", prlist);
			// TODO
			Transaction.commit();
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}

		double currBalance = (double) user.getBalance() / 100;
		request.setAttribute("ava_bal", String.format("%.2f", currBalance));

		if ("submit".equals(request.getParameter("buyfund_btn"))) {
			BuyForm form = new BuyForm(request);

			if (form.isComplete()) {
				// check if fund exists
				Fund fund = fundDAO.read(form.getFund_id());
				if (fund == null) {
					request.setAttribute("op_fail", "Fund not exist.");
					return "cm_buyfund.jsp";
				}

				// check if balance is enough
				if (form.getAmount() > user.getBalance()) {
					request.setAttribute("op_fail",
							"You do not have enough balance");
					return "cm_buyfund.jsp";
				}

				// TODO
				try {
					Transaction.begin();
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

					Transaction.commit();
				} catch (RollbackException e) {
					if (Transaction.isActive()) {
						Transaction.rollback();
					}
				}
				// TODO

				return "cm_buyfund.jsp";

			} else {
				request.setAttribute("op_fail", "Operation failed");
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

}

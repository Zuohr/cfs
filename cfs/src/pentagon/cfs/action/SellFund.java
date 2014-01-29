/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.Position;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.SellForm;
import pentagon.cfs.model.Model;

public class SellFund implements Action {
	private Model model;

	public SellFund(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("customer");
		if (user == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_cmsellfund", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name",
				user.getFirstname() + " " + user.getLastname());
		PositionDAO posDAO = model.getPositionDAO();
		Position[] pos = posDAO.getPositions(user.getId());
		FundDAO fundDAO = model.getFundDAO();
		SellPositionList[] plist = new SellPositionList[pos.length];

		for (int i = 0; i < pos.length; i++) {
			Fund fd = fundDAO.read(pos[i].getFund_id());
			plist[i] = new SellPositionList(fd.getName(), pos[i].getShare(),
					pos[i].getSharebalance(), fd.getId());
		}
		request.setAttribute("cus_position", plist);

		if ("submit".equals(request.getParameter("sellfund_btn"))) {
			SellForm form = new SellForm(request);

			// check if form is complete
			if (form.isComplete()) {
				Fund fund = fundDAO.read(Integer.valueOf(form.getFund_id()));
				// check if fund exists
				if (fund == null) {
					return "cm_sellfund.jsp";
				}

				long inputAmount = form.getShare();
				Position currPos = posDAO.getCmPosition(user.getId(),
						fund.getId());
				// check if position exists
				if (currPos == null) {
					request.setAttribute("op_fail",
							"You do not have enough balance.");
					return "cm_sellfund.jsp";
				}

				long availAmount = currPos.getSharebalance();
				// check available balance
				if (inputAmount > availAmount) {
					System.out.println("should be here");
					request.setAttribute("op_fail",
							"You do not have enough balance.");
					return "cm_sellfund.jsp";
				} else {
					// succeed
					// update transaction
					TransactionDAO transDao = model.getTransactionDAO();
					TransactionRecord rd = new TransactionRecord();
					rd.setCm_id(user.getId());
					rd.setType("sell");
					rd.setShare(inputAmount);
					rd.setFund_id(fund.getId());
					rd.setComplete(false);
					transDao.create(rd);
					request.setAttribute("op_success",
							"You have successfully placed the order");

					// update available share
					availAmount -= inputAmount;
					currPos.setSharebalance(availAmount);
					posDAO.update(currPos);

					// update position list
					pos = posDAO.getPositions(user.getId());
					plist = new SellPositionList[pos.length];

					for (int i = 0; i < pos.length; i++) {
						Fund fd = fundDAO.read(pos[i].getFund_id());
						plist[i] = new SellPositionList(fd.getName(),
								pos[i].getShare(), pos[i].getSharebalance(),
								fd.getId());
					}
					request.setAttribute("cus_position", plist);
					return "cm_sellfund.jsp";
				}
			} else {
				request.setAttribute("op_fail",
						"Operation failed");
				request.setAttribute("errors", form.getErrors());
				return "cm_sellfund.jsp";
			}
		} else {
			return "cm_sellfund.jsp";
		}
	}

	// bean for fund position display in jsp
	public class SellPositionList {
		private String fundName;
		private int fundId;
		private String share;
		private String shareBalance;

		public SellPositionList(String fundName, long share, long shareBalance,
				int fundId) {
			this.fundName = fundName;
			this.share = String.format("%.3f", (double) share / 1000);
			this.shareBalance = String.format("%.3f",
					(double) shareBalance / 1000);
			this.fundId = fundId;
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

		public int getFundId() {
			return fundId;
		}
	}

	@Override
	public String getName() {
		return "sellfund.do";
	}

}

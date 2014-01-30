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

import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.Position;
import pentagon.cfs.databean.PositionRecord;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.SellForm;
import pentagon.cfs.model.CommonUtil;
import pentagon.cfs.model.Model;

public class SellFund implements Action {
	private Model model;

	public SellFund(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Customer user = (Customer) request.getSession()
				.getAttribute("customer");
		if (user == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_cmsellfund", "active");
		request.setAttribute("header_type", "Customer");
		request.setAttribute("header_name",
				user.getFirstname() + " " + user.getLastname());

		PositionDAO posDAO = model.getPositionDAO();
		FundDAO fundDAO = model.getFundDAO();
		// TODO
		try {
			Transaction.begin();
			// set position list
			Position[] pos = posDAO.getPositions(user.getId());
			PositionRecord[] plist = new PositionRecord[pos.length];
			for (int i = 0; i < pos.length; i++) {
				Fund fd = fundDAO.read(pos[i].getFund_id());
				plist[i] = new PositionRecord(CommonUtil.getResearchURL(fd),
						pos[i].getShare(), pos[i].getSharebalance(), 0);
				plist[i].setFundId(fd.getId());
			}
			request.setAttribute("cus_position", plist);

			// TODO
			Transaction.commit();
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}

		if ("submit".equals(request.getParameter("sellfund_btn"))) {
			SellForm form = new SellForm(request);

			// check if form is complete
			if (form.isComplete()) {
				// TODO
				try {
					Transaction.begin();
					Fund fund = fundDAO
							.read(Integer.valueOf(form.getFund_id()));
					// check if fund exists
					if (fund == null) {
						request.setAttribute("op_fail", "Fund not exist.");
					} else {

						long inputAmount = form.getShare();
						Position currPos = posDAO.getCmPosition(user.getId(),
								fund.getId());
						// check if position exists
						if (currPos == null) {
							request.setAttribute("op_fail",
									"You do not have share of this fund.");
						} else {

							long availAmount = currPos.getSharebalance();
							// check available balance
							if (inputAmount > availAmount) {
								request.setAttribute("op_fail",
										"You do not have enough balance.");
							} else {
								// succeed
								// update transaction
								TransactionDAO transDao = model
										.getTransactionDAO();
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
								Position[] pos = posDAO.getPositions(user
										.getId());
								PositionRecord[] plist = new PositionRecord[pos.length];
								for (int i = 0; i < pos.length; i++) {
									Fund fd = fundDAO.read(pos[i].getFund_id());
									plist[i] = new PositionRecord(
											CommonUtil.getResearchURL(fd),
											pos[i].getShare(),
											pos[i].getSharebalance(), 0);
									plist[i].setFundId(fd.getId());
								}
								request.setAttribute("cus_position", plist);
							}
						}
					}
					// TODO
					Transaction.commit();
				} catch (RollbackException e) {
					if (Transaction.isActive()) {
						Transaction.rollback();
					}
				}
				return "cm_sellfund.jsp";
			} else {
				request.setAttribute("op_fail", "Operation failed");
				request.setAttribute("errors", form.getErrors());
				return "cm_sellfund.jsp";
			}
		} else {
			return "cm_sellfund.jsp";
		}
	}

	@Override
	public String getName() {
		return "sellfund.do";
	}

}

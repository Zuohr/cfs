/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

// hzuo

package pentagon.cfs.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.MetaDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.Position;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.TransitionForm;
import pentagon.cfs.model.CommonUtil;
import pentagon.cfs.model.Model;

public class TransitionDay implements Action {
	private Model model;
	public static final String REJ_FLAG = "rej_";

	public TransitionDay(Model model) {
		super();
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee empl = (Employee) request.getSession()
				.getAttribute("employee");
		if (empl == null) {
			return "login.jsp";
		}

		request.setAttribute("nav_eetransition", "active");
		request.setAttribute("header_type", "Employee");
		request.setAttribute("header_name",
				empl.getFirstname() + " " + empl.getLastname());

		// set fund list
		FundDAO fundDAO = model.getFundDAO();
		// TODO
		try {
			Transaction.begin();
			Fund[] fund_list = fundDAO.match();
			for (Fund fund : fund_list) {
				fund.setName(CommonUtil.getResearchURL(fund));
			}
			request.setAttribute("fund_list", fund_list);
			request.setAttribute("fund_num", fund_list.length);

			// set last trading day
			Meta meta = model.getMetaDAO().read(Integer.valueOf(1));
			if (meta != null) {
				Meta.lastDate = meta.getDate();
				request.setAttribute("last_day", Meta.lastDate);
			} else {
				request.setAttribute("last_day", "-");
			}
			// TODO
			Transaction.commit();
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}

		if ("submit".equals(request.getParameter("btn_transition"))) {
			String numInput = request.getParameter("fund_num");
			int fund_num = -1;
			if (numInput == null || !numInput.matches("\\d+")) {
				return "ee_transition.jsp";
			} else {
				try {
					fund_num = Integer.parseInt(numInput);
					if (fund_num != fundDAO.getCount()) {
						return "ee_transition.jsp";
					}
				} catch (NumberFormatException e) {
					return "ee_transition.jsp";
				}
			}
			TransitionForm form = new TransitionForm(request, fund_num);
			if (form.isComplete()) {
				// TODO
				try {
					Transaction.begin();

					Date tradingDate = form.getDate();

					// set fund price
					ArrayList<FundPriceHistory> price_list = form
							.getFundPrices();
					FundPriceHistoryDAO fphDAO = model.getFundPriceHistoryDAO();
					if (price_list.size() != fundDAO.getCount()) {
						request.setAttribute("op_fail",
								"Fund list expired, please fill again.");
						Fund[] fund_list = fundDAO.match();
						for (Fund fund : fund_list) {
							fund.setName(CommonUtil.getResearchURL(fund));
						}
						request.setAttribute("fund_list", fund_list);
						request.setAttribute("fund_num", fund_list.length);
					} else {
						HashMap<Integer, Long> priceMap = new HashMap<Integer, Long>();
						for (FundPriceHistory item : price_list) {
							fphDAO.create(item);
							priceMap.put(item.getFund_id(), item.getPrice());
						}

						// process transaction
						TransactionDAO tranDAO = model.getTransactionDAO();
						TransactionRecord[] pendings = tranDAO.getPending();
						CustomerDAO cmDAO = model.getCustomerDAO();
						PositionDAO posDAO = model.getPositionDAO();
						int succ = 0, rej = 0;
						for (TransactionRecord rd : pendings) {
							if ("buy".equals(rd.getType())) {
								int fund_id = rd.getFund_id();
								long price = priceMap.get(fund_id);
								long amount = rd.getAmount();
								double share = (double) amount / price;
								Position currPos = posDAO.getCmPosition(
										rd.getCm_id(), rd.getFund_id());
								long currShare = 0;
								if (currPos != null) {
									currShare = currPos.getShare();
								}
								if (CommonUtil.canAdd(currShare,
										(long) (share * 1000))) {
									rd.setShare((long) (share * 1000));
									rd.setComplete(true);
									rd.setDate(tradingDate);
									tranDAO.update(rd);

									Customer cm = cmDAO.read(Integer.valueOf(rd
											.getCm_id()));
									cm.setCash(cm.getCash()
											- (long) (price * share));
									cm.setBalance(cm.getCash());
									cm.setLasttrading(tradingDate);
									cmDAO.update(cm);

									posDAO.updatePosition(rd.getCm_id(),
											rd.getFund_id(),
											(long) (share * 1000));

									succ++;
								} else {
									rd.setComplete(true);
									rd.setDate(tradingDate);
									rd.setType(REJ_FLAG + rd.getType());
									tranDAO.update(rd);

									Customer cm = cmDAO.read(Integer.valueOf(rd
											.getCm_id()));
									cm.setBalance(cm.getCash());
									cm.setLasttrading(tradingDate);
									cmDAO.update(cm);

									rej++;
								}
							} else if ("sell".equals(rd.getType())) {
								int fund_id = rd.getFund_id();
								long price = priceMap.get(fund_id);
								long share = rd.getShare();
								Customer cm = cmDAO.read(Integer.valueOf(rd
										.getCm_id()));
								if (CommonUtil.canMultiply(price, share)
										&& CommonUtil
												.canAdd(cm.getCash(),
														(long) (((double) price
																/ 100
																* (double) share / 1000) * 100))) {
									long amount = (long) (((double) price / 100
											* (double) share / 1000) * 100);
									rd.setAmount(amount);
									rd.setComplete(true);
									rd.setDate(tradingDate);
									tranDAO.update(rd);

									cm.setCash(cm.getCash() + amount);
									cm.setBalance(cm.getCash());
									cm.setLasttrading(tradingDate);
									cmDAO.update(cm);

									posDAO.updatePosition(rd.getCm_id(),
											rd.getFund_id(), -1 * share);

									succ++;
								} else {
									rd.setComplete(true);
									rd.setDate(tradingDate);
									rd.setType(REJ_FLAG + rd.getType());
									tranDAO.update(rd);

									cm.setLasttrading(tradingDate);
									cmDAO.update(cm);

									// revert balance
									Position pos = posDAO.getCmPosition(
											rd.getCm_id(), rd.getFund_id());
									pos.setSharebalance(pos.getShare());
									posDAO.update(pos);

									rej++;
								}
							} else if ("deposit".equals(rd.getType())) {
								long amount = rd.getAmount();
								Customer cm = cmDAO.read(Integer.valueOf(rd
										.getCm_id()));
								if (CommonUtil.canAdd(cm.getCash(), amount)) {
									cm.setCash(cm.getCash() + amount);
									cm.setBalance(cm.getCash());
									cm.setLasttrading(tradingDate);
									cmDAO.update(cm);
									succ++;
								} else {
									cm.setLasttrading(tradingDate);
									cmDAO.update(cm);
									rej++;
									rd.setType(REJ_FLAG + rd.getType());
								}

								rd.setComplete(true);
								rd.setDate(tradingDate);
								tranDAO.update(rd);
							} else if ("withdraw".equals(rd.getType())) {
								long amount = rd.getAmount();
								Customer cm = cmDAO.read(Integer.valueOf(rd
										.getCm_id()));
								cm.setCash(cm.getCash() - amount);
								cm.setBalance(cm.getCash());
								cm.setLasttrading(tradingDate);
								cmDAO.update(cm);

								rd.setComplete(true);
								rd.setDate(tradingDate);
								tranDAO.update(rd);

								succ++;
							}
						}

						// set last trading day
						MetaDAO metaDAO = model.getMetaDAO();
						String lastDate = new SimpleDateFormat(Meta.DATE_FORMAT)
								.format(tradingDate);
						Meta m = new Meta();
						m.setDate(lastDate);
						if (metaDAO.getCount() == 0) {
							metaDAO.create(m);
						} else {
							m.setId(1);
							metaDAO.update(m);
						}

						Meta.lastDate = lastDate;

						// set result
						if (succ > 0) {
							request.setAttribute(
									"op_success",
									String.format(
											"%d online transaction%s successfully processed on %s.",
											succ, (succ > 1 ? "s" : ""),
											Meta.lastDate));
						}
						if (rej > 0) {
							request.setAttribute(
									"op_fail",
									String.format(
											"%d online transaction%s rejected on %s : request amount exceed online system limit.",
											rej, (succ > 1 ? "s" : ""),
											Meta.lastDate));
						}
						if (succ == 0 && rej == 0) {
							request.setAttribute(
									"op_success",
									String.format(
											"Fund price set, no online transaction to process on %s.",
											Meta.lastDate));
						}
						request.setAttribute("last_day", Meta.lastDate);
					}
					// TODO
					Transaction.commit();
				} catch (RollbackException e) {
					e.printStackTrace();
					request.setAttribute("op_fail",
							"Operation failed : internal error.");
				} finally {
					if (Transaction.isActive()) {
						Transaction.rollback();
					}
				}
				return "ee_transition.jsp";
			} else {
				ArrayList<String> errors = form.getErrors();
				request.setAttribute("errors", errors);
				request.setAttribute("op_fail", "Operation failed.");
				return "ee_transition.jsp";
			}
		} else if ("cancel".equals(request.getParameter("btn_transition"))) {
			return "emplviewcmlist.do";
		} else {
			return "ee_transition.jsp";
		}
	}

	@Override
	public String getName() {
		return "transition.do";
	}

}

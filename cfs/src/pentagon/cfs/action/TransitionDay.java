// hzuo

package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.FundPriceHistoryDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.TransitionForm;
import pentagon.cfs.model.Model;

public class TransitionDay implements Action {
	private Model model;

	public TransitionDay(Model model) {
		super();
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee empl = (Employee) request.getSession().getAttribute("employee");
		if (empl == null) {
			return "login.do";
		} else {
			FundDAO dao = model.getFundDAO();
			Fund[] fund_list = dao.match();
			request.setAttribute("fund_list", fund_list);
			request.setAttribute("fund_num", fund_list.length);
			request.setAttribute("last_day", Meta.lastDate);
			if ("submit".equals(request.getParameter("btn_transition"))) {
				TransitionForm form = new TransitionForm(request);
				if (form.isComplete()) {
					// request.setAttribute("result", "bingo");
					try {
						Transaction.begin();

						Date tradingDate = form.getDate();

						// set fund price
						ArrayList<FundPriceHistory> price_list = form
								.getFundPrices();
						FundPriceHistoryDAO fphDAO = model
								.getFundPriceHistoryDAO();
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
						for (TransactionRecord rd : pendings) {
							if ("buy".equals(rd.getType())) {
								int fund_id = rd.getFund_id();
								long price = priceMap.get(fund_id);
								long amount = rd.getAmount();
								double share = (double) amount / price;
								rd.setShare((long) share * 1000);
								rd.setComplete(true);
								rd.setDate(tradingDate);

								Customer cm = cmDAO.read(Integer.valueOf(rd
										.getCm_id()));
								cm.setCash(cm.getCash()
										- (long) (price * share) * 100);
								cm.setBalance(cm.getCash());
								cmDAO.update(cm);

								posDAO.updatePosition(rd.getCm_id(),
										rd.getFund_id(), (long) share * 1000);
							} else if ("sell".equals(rd.getType())) {

							} else if ("deposit".equals(rd.getType())) {

							} else if ("withdraw".equals(rd.getType())) {

							}
						}

						// set last trading day
						request.setAttribute("result",
								pendings.length + " transactions processed on "
										+ form.getTradingDay());

						Transaction.commit();
					} catch (RollbackException e) {
						e.printStackTrace();// TODO
					} finally {
						if (Transaction.isActive()) {
							Transaction.rollback();
							request.setAttribute("result", "Operation failed.");
						}
					}
					return "ee_transition.jsp";
				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "ee_transition.jsp";
				}
			} else if ("cancel".equals(request.getParameter("btn_transition"))) {
				return "empl_main.jsp";
			} else {
				return "ee_transition.jsp";
			}
		}
	}

	@Override
	public String getName() {
		return "transition.do";
	}

}

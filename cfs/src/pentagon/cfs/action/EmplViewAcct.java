/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.Position;
import pentagon.cfs.model.Model;

public class EmplViewAcct implements Action {
	private Model model;

	public EmplViewAcct(Model model) {
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		Employee user = (Employee) request.getSession()
				.getAttribute("employee");
		if (user == null) {
			return "login.jsp";
		} else {
			String username = request.getParameter("usr");
			if (username == null || username.isEmpty()) {
				return "emplviewcmlist.do";
			} else {
				CustomerDAO cmDAO = model.getCustomerDAO();
				Customer customer = cmDAO.getProfile(username);
				if (customer == null) {
					return "emplviewcmlist.do";
				} else {
					double cash = (double) customer.getCash() / 100;
					request.setAttribute("cash", String.format("%.2f", cash));
					if (customer.getLasttrading() == null) {
						request.setAttribute("lastTradingDay", "-");
					} else {
						request.setAttribute("lastTradingDay",
								new SimpleDateFormat(Meta.DATE_FORMAT)
										.format(customer.getLasttrading()));
					}
					request.setAttribute("view_customer", customer);

					PositionDAO posDAO = model.getPositionDAO();
					Position[] pos = posDAO.getPositions(customer.getId());
					PositionRecord[] posRd = new PositionRecord[pos.length];
					for (int i = 0; i < pos.length; i++) {
						posRd[i] = new PositionRecord(pos[i]);
					}
					request.setAttribute("cus_position", posRd);
					return "ee_viewcmacct.jsp";
				}
			}
		}
	}

	@Override
	public String getName() {
		return "emplviewacct.do";
	}

	public class PositionRecord {
		private String fundName;
		private double share;

		public PositionRecord(Position pos) throws RollbackException {
			FundDAO fundDAO = model.getFundDAO();
			Fund fund = fundDAO.read(Integer.valueOf(pos.getFund_id()));
			this.fundName = fund.getName();

			long shareLong = pos.getShare();
			this.share = (double) shareLong / 1000;
		}

		public String getFundName() {
			return fundName;
		}

		public double getShare() {
			return share;
		}
	}
}

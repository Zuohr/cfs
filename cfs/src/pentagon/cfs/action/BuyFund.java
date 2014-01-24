/*Hao Ge
 * 
 * */
package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.BuyForm;
import pentagon.cfs.model.Model;

public class BuyFund implements Action {
	private Model model;
	
	public BuyFund(Model model){
		this.model = model;
	}
	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("customer");
		if(user ==null){
			return "login.do";
		} else{
			FundDAO fundDAO = model.getFundDAO();
			Fund[] fund_list = fundDAO.match();
			request.setAttribute("fund_list", fund_list);
			
			request.setAttribute("ava_bal", user.getBalance());
			
			if("submit".equals(request.getParameter("buyfund_btn"))){
				BuyForm form = new BuyForm(request);
				
				if(form.isComplete()){
					if(form.getDeposit()>user.getBalance()){
						request.setAttribute("order_fail", "You do not have enough balance");
						return "cm_buyfund.jsp";
					}
					TransactionDAO dao = model.getTransactionDAO();
					TransactionRecord record = form.getBuyFund();
					record.setCm_id(user.getId());
					record.setComplete(false);
					dao.create(record);
					request.setAttribute("order_succ", "You have successfully placed the order");
				} else{
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
				}
			}
			return "cm_buyfund.jsp";
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "buyfund.do";
	}

}

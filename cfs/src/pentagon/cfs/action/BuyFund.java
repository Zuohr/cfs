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
	
	public BuyFund(Model model){
		this.model = model;
	}
	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("customer");
		if(user ==null){
			return "login.jsp";
		} else{
			request.setAttribute("nav_cmbuyfund", "active");
			FundDAO fundDAO = model.getFundDAO();
			Fund[] fund_list = fundDAO.match();
			FundPriceHistoryDAO fphDAO = model.getFundPriceHistoryDAO(); 
			
			BuyFundList[] bflist = new BuyFundList[fund_list.length];
			for(int i=0; i<fund_list.length; i++){
				FundPriceHistory[] fph = fphDAO.getHistory(fund_list[i].getId());
				double bf_price = 0;
				if(fph.length!=0){
					bf_price = (double)fph[fph.length-1].getPrice()/100;
				}
				bflist[i] = new BuyFundList(fund_list[i].getName(), fund_list[i].getSymbol(), 
						fund_list[i].getId(), bf_price);
			}
			
			CustomerDAO cmDAO = model.getCustomerDAO();
			Customer cmBean = cmDAO.read(user.getId());
			
			request.setAttribute("bf_list", bflist);
			
			double bal = ((double) cmBean.getBalance())/100; 
			
			request.setAttribute("ava_bal", bal);
			
			if("submit".equals(request.getParameter("buyfund_btn"))){
				BuyForm form = new BuyForm(request);
				
				if(form.isComplete()){
					if(form.getDeposit()>user.getBalance()){
						request.setAttribute("op_fail", "You do not have enough balance");
						return "cm_buyfund.jsp";
					}
					TransactionDAO dao = model.getTransactionDAO();
					TransactionRecord record = form.getBuyFund();
					record.setCm_id(user.getId());
					record.setComplete(false);
					dao.create(record);
					request.setAttribute("op_success", "You have successfully placed the order");
					
					bal = bal-form.getDeposit();

					cmBean.setBalance((long)bal*100);
					cmDAO.update(cmBean);
					
					fund_list = fundDAO.match();
					fphDAO = model.getFundPriceHistoryDAO(); 
					
					bflist = new BuyFundList[fund_list.length];
					for(int i=0; i<fund_list.length; i++){
						FundPriceHistory[] fph = fphDAO.getHistory(fund_list[i].getId());
						double bf_price = 0;
						if(fph.length!=0){
							bf_price = (double)fph[fph.length-1].getPrice()/100;
						}
						bflist[i] = new BuyFundList(fund_list[i].getName(), fund_list[i].getSymbol(), 
								fund_list[i].getId(), bf_price);
					}
					request.setAttribute("bf_list", bflist);
					request.setAttribute("ava_bal", bal);
					
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

	public class BuyFundList {
		private String fundName;
		private String ticker;
		private int fundId;
		private double price;
		
		public BuyFundList(String fundName, String ticker, int fundId, double price){
			this.fundName = fundName;
			this.ticker = ticker;
			this.fundId = fundId;
			this.price = price;
		}
		
		public String getFundName(){
			return fundName;
		}
		
		public String getTicker(){
			return ticker;
		}
		
		public int getFundId(){
			return fundId;
		}
		
		public double getPrice(){
			return price;
		}
	}
}

/*Hao Ge
 * 
 * 
 * */
package pentagon.cfs.action;

import java.util.ArrayList;

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
	
	public SellFund(Model model){
		this.model = model;
	}
	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("customer");
		if(user ==null){
			return "login.do";
		} else{
			PositionDAO posDAO = model.getPositionDAO();
			Position[] pos = posDAO.getPositions(user.getId());
			FundDAO fundDAO = model.getFundDAO();
			SellPositionList[] plist = new SellPositionList[pos.length];
			
			for(int i=0; i<pos.length; i++){
				Fund fd = fundDAO.read(pos[i].getFund_id());
				plist[i] = new SellPositionList(fd.getName(), 
						(double)pos[i].getShare()/1000, (double)pos[i].getSharebalance()/1000, fd.getId());
			}
			request.setAttribute("cus_position", plist); 
			
			if("submit".equals(request.getParameter("sellfund_btn"))){
				SellForm form = new SellForm(request);
				
				if(form.isComplete()){
					long inputFund = (long)form.getFundAmount()*1000;
					long avaShares = posDAO.getCmPosition(user.getId(), form.getFundId()).getSharebalance();
					if(inputFund>avaShares){
						request.setAttribute("order_fail", "You do not have enough balance");
						return "cm_buyfund.jsp";
					}
					TransactionDAO dao = model.getTransactionDAO();
					TransactionRecord record = form.getSellFund();
					dao.create(record);
					request.setAttribute("order_succ", "You have successfully placed the order");
					
					long balance = avaShares-inputFund;
					Position cmPos = posDAO.getCmPosition(user.getId(), form.getFundId());
					cmPos.setSharebalance(balance);
					posDAO.updatePosGeneric(cmPos);
					
					
					pos = posDAO.getPositions(user.getId());
					fundDAO = model.getFundDAO();
					plist = new SellPositionList[pos.length];
					
					for(int i=0; i<pos.length; i++){
						Fund fd = fundDAO.read(pos[i].getFund_id());
						plist[i] = new SellPositionList(fd.getName(), 
								(double)pos[i].getShare()/1000, (double)pos[i].getSharebalance()/1000, fd.getId());
					}
					request.setAttribute("cus_position", plist); 
				}
				else{
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					return "cm_sellfund.jsp";
				}
			} else{
				return "cm_sellfund.jsp";
			}
			

		}
		return "cm_sellfund.jsp";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "sellfund.do";
	}
	public class SellPositionList {
		private String fundName;
		private int fundId;
		private double share;
		private double shareBalance;
		
		public SellPositionList(String fundName, double share, double shareBalance, int fundId){
			this.fundName = fundName;
			this.share=share;
			this.shareBalance = shareBalance;
			this.fundId = fundId;
		}

		public String getFundName() {
			return fundName;
		}

		public double getShare() {
			return share;
		}

		public double getShareBalance() {
			return shareBalance;
		}
		
		public int getFundId(){
			return fundId;
		}
	}

}

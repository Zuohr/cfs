/*Hao Ge
 * 
 * 
 * */
package pentagon.cfs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import pentagon.cfs.action.EmplViewAcct.PositionList;
import pentagon.cfs.dao.FundDAO;
import pentagon.cfs.dao.PositionDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Customer;
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
			PositionList[] plist = new PositionList[pos.length];
			for(int i=0; i<pos.length; i++){
				plist[i] = new PositionList(fundDAO.read(pos[i].getFund_id()).getName(), pos[i].getShare()/1000);
			}
			request.setAttribute("cus_position", plist); 
			
			if("submit".equals(request.getParameter("sellfund_btn"))){
				SellForm form = new SellForm(request);
				
				if(form.isComplete()){
					TransactionDAO dao = model.getTransactionDAO();
					TransactionRecord record = form.getSellFund();
					dao.create(record);
					
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
	public class PositionList {
		private String fundName;
		private double share;
		
		public PositionList(String fundName, double share){
			this.fundName = fundName;
			this.share=share;
		}
	}

}

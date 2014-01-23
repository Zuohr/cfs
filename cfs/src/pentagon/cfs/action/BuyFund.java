/*Hao Ge
 * 
 * */
package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import pentagon.cfs.model.Model;

public class BuyFund implements Action {
	private Model model;
	
	public BuyFund(Model model){
		this.model = model;
	}
	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "buyfund.do";
	}

}

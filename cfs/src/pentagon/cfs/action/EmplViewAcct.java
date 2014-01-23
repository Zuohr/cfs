/*Hao Ge
 * 
 * 
 * */
package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.model.Model;

public class EmplViewAcct implements Action{
	private Model model;
	
	public EmplViewAcct(Model model) {
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
		return "emplviewacct.do";
	}

}

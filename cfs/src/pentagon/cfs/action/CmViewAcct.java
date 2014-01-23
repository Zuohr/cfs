/*Hao Ge
 * 
 * 
 * */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.model.Model;

public class CmViewAcct implements Action{
	private Model model;

	public CmViewAcct (Model model) {
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
		return "cmviewacct.do";
	}

}

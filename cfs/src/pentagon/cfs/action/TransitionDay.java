// hzuo

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

public class TransitionDay implements Action {

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		return null;
	}

	@Override
	public String getName() {
		return "transition.do";
	}

}

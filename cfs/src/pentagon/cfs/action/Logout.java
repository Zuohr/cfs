/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

public class Logout implements Action {

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		request.getSession().removeAttribute("customer");
		request.getSession().removeAttribute("employee");
		return "login.jsp";
	}

	@Override
	public String getName() {
		return "logout.do";
	}

}

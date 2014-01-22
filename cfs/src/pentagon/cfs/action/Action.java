/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

//hzuo

package pentagon.cfs.action;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

public interface Action {
	public String perform(HttpServletRequest request) throws RollbackException;

	public String getName();
}

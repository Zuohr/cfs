/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class CreateFund implements Action {

	@Override
	public String perform(HttpServletRequest request) throws SQLException {
		return null;
	}

	@Override
	public String getName() {
		return "createfund.do";
	}

}

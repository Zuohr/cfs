// hzuo

package pentagon.cfs.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class ResearchFund implements Action {

	@Override
	public String perform(HttpServletRequest request) throws SQLException {
		return null;
	}

	@Override
	public String getName() {
		return "researchfund.do";
	}

}

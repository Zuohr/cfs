package pentagon.cfs.model;

import pentagon.cfs.databean.Fund;

public class CommonUtil {
	public static String getResearchURL(Fund fund) {
		return String
				.format("<a href=\"researchfund.do?fund_id=%d\" target=\"_blank\">%s (%s)</a>",
						fund.getId(), fund.getName(), fund.getSymbol());
	}
}

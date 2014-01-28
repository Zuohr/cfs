package pentagon.cfs.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pentagon.cfs.databean.Fund;


public class CommonUtil {
	public static String getResearchURL(Fund fund) {
		return String
				.format("<a href=\"researchfund.do?fund_id=%d\" target=\"_blank\">%s (%s)</a>",
						fund.getId(), fund.getName(), fund.getSymbol());
	}
	
	public boolean isLegal(String s){
		s = s.trim();
		if(s.length()>80){
			return false;
		}
	    Pattern pattern = Pattern.compile("[~#*+%{}<>\\[\\]|\"\\_^]");
	    Matcher matcher = pattern.matcher(s);
	    return matcher.find();
	}
}

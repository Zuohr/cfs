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

	public static boolean isLegal(String s) {
		Pattern pattern = Pattern.compile("[~#*+%{}<>\\[\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(s);
		return !matcher.find() && s.length() <= 60;
	}

	public static boolean isTooLong(String s) {
		return s.length() > 60;
	}

	public static long getNumber(String amountInput, int precision) {
		double base = 1;
		for (int i = 0; i < precision; i++) {
			base *= 10;
		}
		if (amountInput == null || amountInput.trim().isEmpty()) {
			throw new RuntimeException("Amount can not be empty.");
		} else if (!amountInput.matches("\\d*\\.?\\d+")) {
			throw new RuntimeException("Please provide a valid number.");
		} else {
			try {
				Double inputParse = Double.parseDouble(amountInput);
				if (inputParse.isInfinite()
						|| inputParse * base + 10000 > Long.MAX_VALUE) {
					throw new RuntimeException(
							"Amount exceed system limit, please put a smaller value.");
				} else if (inputParse < (1 / base)) {
					throw new RuntimeException(String.format(
							"The minimum amount is %s.",
							String.valueOf(1 / base)));
				} else {
					return (long) (inputParse * base);
				}
			} catch (NumberFormatException e) {
				throw new RuntimeException("Invalid number.");
			}
		}
	}

}

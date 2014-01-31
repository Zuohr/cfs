/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.Fund;
import pentagon.cfs.model.CommonUtil;

public class CreateFundForm {
	private String fundName;
	private String fundTicker;
	private boolean complete = true;
	private ArrayList<String> errors;

	public CreateFundForm(HttpServletRequest request) {
		this.fundName = (String) request.getParameter("fundname");
		this.fundTicker = (String) request.getParameter("ticker");
		int size = 2;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public Fund getFund() {
		Fund fund = new Fund();
		fund.setName(fundName);
		fund.setSymbol(fundTicker);
		return fund;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public boolean isComplete() {
		return complete;
	}

	private void checkErrors() {
		if (fundName == null || fundName.trim().isEmpty()) {
			errors.set(0, "Fund name cannot be empty.");
			complete = false;
		} else if (!CommonUtil.isLegal(fundName)) {
			errors.set(0,
					"Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}
		
		if (fundTicker == null || fundTicker.trim().isEmpty()) {
			errors.set(1, "Fund ticker cannot be empty");
			complete = false;
		} else if (!CommonUtil.isLegal(fundTicker)) {
			errors.set(1,
					"Invalid input : contains special character or too long (maximum 5).");
			complete = false;
		} else if (!fundTicker.matches("[a-zA-Z]{1,5}")) {
			errors.set(1, "Ticker allows only 1 to 5 letters.");
			complete = false;
		}
	}

}

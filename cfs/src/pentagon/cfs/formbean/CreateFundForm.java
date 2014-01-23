package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.Fund;

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
		}
		if (fundTicker == null || fundTicker.trim().isEmpty()) {
			errors.set(1, "Fund ticker cannot be empty");
			complete = false;
		}
	}

}

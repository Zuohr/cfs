/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.model.CommonUtil;

public class BuyForm {
	private String idInput;
	private String amountInput;
	private long amount;
	private int fund_id;
	private boolean complete = true;
	private ArrayList<String> errors;

	public BuyForm(HttpServletRequest request) {
		this.idInput = request.getParameter("fundId");
		this.amountInput = request.getParameter("buyAmount");
		int size = 2;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public int getFund_id() {
		return fund_id;
	}

	public long getAmount() {
		return amount;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public boolean isComplete() {
		return complete;
	}

	private void checkErrors() {
		if (idInput == null
				|| !idInput.matches("\\d+")
				|| idInput.length() > 10
				|| (idInput.length() == 10 && idInput.compareTo(String
						.valueOf(Integer.MAX_VALUE)) > 0)) {
			errors.set(0, "Invalid fund ID.");
			complete = false;
		} else {
			fund_id = Integer.parseInt(idInput);
		}

		try {
			this.amount = CommonUtil.getNumber(amountInput, 2);
		} catch (RuntimeException e) {
			errors.set(1, e.getMessage());
			complete = false;
		}
	}
}

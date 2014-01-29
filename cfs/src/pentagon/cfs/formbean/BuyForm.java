/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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

		if (amountInput == null || amountInput.trim().isEmpty()) {
			errors.set(1, "Please provide amount.");
			complete = false;
		} else {
			try {
				double inputParse = Double.parseDouble(amountInput);
				double max = (double) Long.MAX_VALUE / 100;
				if (inputParse > max) {
					errors.set(1, "Amount too large.");
					complete = false;
				} else if (inputParse < 0.01) {
					errors.set(1, "The minimum amount is 0.01.");
					complete = false;
				} else {
					amount = (long) (inputParse * 100);
				}
			} catch (NumberFormatException e) {
				errors.set(1, "Invalid number.");
				complete = false;
			}
		}
	}
}

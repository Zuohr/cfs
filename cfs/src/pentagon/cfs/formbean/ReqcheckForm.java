package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ReqcheckForm {
	private long amount;
	private String amountInput;
	private ArrayList<String> errors;
	private boolean complete = true;

	public ReqcheckForm(HttpServletRequest request) {
		amountInput = request.getParameter("request_amount");
		int size = 1;
		errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public boolean isComplete() {
		return complete;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public long getAmount() {
		return amount;
	}

	public void checkErrors() {
		if (amountInput == null || amountInput.isEmpty()) {
			errors.set(0, "Please provide amount.");
			complete = false;
		} else {
			try {
				double inputParse = Double.parseDouble(amountInput);
				double max = (double) Long.MAX_VALUE / 100;
				if (inputParse > max) {
					errors.set(0, "Amount too large.");
					complete = false;
				} else if (inputParse < 0.01) {
					errors.set(0, "The minimum amount is 0.01.");
					complete = false;
				} else {
					amount = (long) (inputParse * 100);
				}
			} catch (NumberFormatException e) {
				errors.set(0, "Invalid number.");
				complete = false;
			}
		}

	}
}

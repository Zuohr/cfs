package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.model.CommonUtil;

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
		try {
			amount = CommonUtil.getNumber(amountInput, 2);
		} catch (RuntimeException e) {
			errors.set(0, e.getMessage());
			complete = false;
		}
	}
}

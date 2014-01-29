package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.TransactionRecord;

public class SellForm {
	private String idInput;
	private String shareAmountInput;
	private long share;
	private int fund_id;
	private boolean complete = true;
	private ArrayList<String> errors;

	public SellForm(HttpServletRequest request) {
		this.idInput = request.getParameter("fundId");
		this.shareAmountInput = request.getParameter("sellAmount");
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

	public long getShare() {
		return share;
	}

	public TransactionRecord getSellFund() {
		TransactionRecord record = new TransactionRecord();
		record.setFund_id(fund_id);
		record.setAmount(share);
		record.setType("sell");
		return record;
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

		if (shareAmountInput == null || shareAmountInput.trim().isEmpty()) {
			errors.set(1, "Please provide amount.");
			complete = false;
		} else {
			try {
				double d = Double.parseDouble(shareAmountInput);
				double max = (double) Long.MAX_VALUE / 1000;
				if (d > max) {
					errors.set(1, "Amount too large.");
					complete = false;
				} else if (d < 0.001) {
					errors.set(1, "The minimum amount is 0.001.");
					complete = false;
				} else {
					share = (long) (d * 1000);
				}
			} catch (NumberFormatException e) {
				errors.set(1, "Invalid number.");
				complete = false;
			}
		}
	}
}

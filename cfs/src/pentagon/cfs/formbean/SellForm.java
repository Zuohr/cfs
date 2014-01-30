package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.model.CommonUtil;

public class SellForm {
	private String idInput;
	private String shareAmountInput;
	private long shareAmount;
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
		return shareAmount;
	}

	public TransactionRecord getSellFund() {
		TransactionRecord record = new TransactionRecord();
		record.setFund_id(fund_id);
		record.setAmount(shareAmount);
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

		try {
			shareAmount = CommonUtil.getNumber(shareAmountInput, Meta.SHARE_PRECISION);
		} catch (RuntimeException e) {
			errors.set(1, e.getMessage());
			complete = false;
		}
	}
}

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

public class DepositForm {

	private String userName;
	private String depositInput;
	private long depositAmount;
	private ArrayList<String> errors;
	private boolean complete = true;

	public String getUserName() {
		return userName;
	}

	public long getDepositAmount() {
		return depositAmount;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public boolean isComplete() {
		return complete;
	}

	public DepositForm(HttpServletRequest request) {
		this.userName = request.getParameter("username");
		this.depositInput = request.getParameter("deposit");
		int size = 2;
		errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public void checkErrors() {
		if (userName == null || userName.isEmpty()) {
			errors.set(0, "Please provide user name.");
			complete = false;
		} else if (!CommonUtil.isLegal(userName)) {
			errors.set(0, "Can not contain special characters.");
			complete = false;
		}
		
		try {
			depositAmount = CommonUtil.getNumber(depositInput, 2);
		} catch (RuntimeException e) {
			errors.set(1, e.getMessage());
			complete = false;
		}

		// if (depositInput == null || depositInput.trim().isEmpty()) {
		// errors.set(1, "Please provide amount.");
		// complete = false;
		// } else if (!depositInput.matches("\\d*.?\\d+")) {
		// errors.set(1, "Please provide a valid number.");
		// complete = false;
		// } else {
		// try {
		// Double inputParse = Double.parseDouble(depositInput);
		// Double max = (double) Long.MAX_VALUE / 100;
		// if (inputParse.isInfinite() || inputParse > max) {
		// errors.set(1,
		// "Amount exceed system limit, please put a smaller value.");
		// complete = false;
		// } else if (inputParse < 0.01) {
		// errors.set(1, "The minimum amount is 0.01.");
		// complete = false;
		// } else {
		// depositAmount = (long) (inputParse * 100);
		// }
		// } catch (NumberFormatException e) {
		// errors.set(1, "Invalid number.");
		// complete = false;
		// }
		// }

		// if (depositInput == null || depositInput.isEmpty()) {
		// errors.set(1, "Please provide deposit amount.");
		// complete = false;
		// } else if (depositInput.length() > String.valueOf(Long.MAX_VALUE)
		// .length()
		// || depositInput.length() == String.valueOf(Long.MAX_VALUE)
		// .length()
		// && depositInput.compareTo(String.valueOf(Long.MAX_VALUE)) > 0) {
		// errors.set(1, "Input too large.");
		// complete = false;
		// } else if (!CommonUtil.isLegal(depositInput)) {
		// errors.set(1,
		// "Can not contain special characters or input is too long.");
		// complete = false;
		// } else {
		// double amount;
		// try {
		// amount = Double.parseDouble(depositInput);
		// if (amount < 0.01) {
		// errors.set(1, "Minimum deposit amount is 0.01");
		// complete = false;
		// } else if (amount > MAX_AMOUNT) {
		// errors.set(1,
		// "Deposit amount for each transaction can not exceed 1 billion.");
		// complete = false;
		// } else {
		// this.depositAmount = (long) (amount * 100);
		// }
		// } catch (NumberFormatException e) {
		// errors.set(1, "Invalid number.");
		// complete = false;
		// }
		// }
	}
}

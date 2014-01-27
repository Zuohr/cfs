/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class DepositForm {

	private String userName;
	private String depositInput;
	private long depositAmount;
	private ArrayList<String> errors;
	private boolean complete = true;

	private static final double MAX_AMOUNT = 1E9;

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
		for(int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public void checkErrors() {
		if(userName == null || userName.isEmpty()) {
			errors.set(0, "Please provide user name.");
			complete = false;
		}
		
		if (depositInput == null || depositInput.isEmpty()) {
			errors.set(1, "Please provide deposit amount.");
			complete = false;
		} else if (depositInput.length() > 13) {
			errors.set(1, "Invalid input.");
			complete = false;
		} else {
			double amount;
			try {
				amount = Double.parseDouble(depositInput);
				if (amount <= 0) {
					errors.set(1, "Deposit amount has to be positive.");
					complete = false;
				} else if (amount > MAX_AMOUNT) {
					errors.set(1,
							"Deposit amount for each transaction can not exceed 1 billion.");
					complete = false;
				} else {
					this.depositAmount = (long) (amount * 100);
				}
			} catch (NumberFormatException e) {
				errors.set(1, "Invalid number.");
				complete = false;
			}
		}
	}
}

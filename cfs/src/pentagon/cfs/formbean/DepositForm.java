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
	}
}

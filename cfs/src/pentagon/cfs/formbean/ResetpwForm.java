package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.model.CommonUtil;

public class ResetpwForm {

	private String newPassword;
	private String checkPassword;
	private String userName;
	private boolean complete = true;
	private ArrayList<String> errors;

	public ResetpwForm(HttpServletRequest request) {

		this.newPassword = (String) request.getParameter("NewPassword");
		this.checkPassword = (String) request.getParameter("CheckPassword");
		this.userName = (String) request.getParameter("UserName");
		int size = 3;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public String getUserName() {
		return userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public boolean isComplete() {
		return complete;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void checkErrors() {
		if (userName == null || userName.trim().isEmpty()) {
			errors.set(0, "User Name is required!");
			complete = false;

		} else if (!CommonUtil.isLegal(userName)) {
			errors.set(0,
					"Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}

		if (newPassword == null || newPassword.trim().isEmpty()) {
			errors.set(1, "New Password is required!");
			complete = false;

		} else if (!CommonUtil.isLegal(newPassword)) {
			errors.set(1,
					"Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}

		if (checkPassword == null || checkPassword.trim().isEmpty()) {
			errors.set(2, "Please comfirm password!");
			complete = false;
		} else if (!CommonUtil.isLegal(checkPassword)) {
			errors.set(2,
					"Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}

		if (complete && !newPassword.equals(checkPassword)) {
			errors.set(1,
					"Please make sure the password you re-enter is correct!");
			complete = false;
		}
	}

}

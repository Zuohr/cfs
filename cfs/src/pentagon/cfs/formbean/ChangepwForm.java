package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.model.CommonUtil;

public class ChangepwForm {
	private String oldPassword;
	private String newPassword;
	private String checkPassword;
	private boolean complete = true;
	private ArrayList<String> errors;

	public ChangepwForm(HttpServletRequest request) {
		this.oldPassword = request.getParameter("OldPassword");
		this.newPassword = request.getParameter("NewPassword");
		this.checkPassword = request.getParameter("CheckPassword");
		int size = 3;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public String getOldPassword() {
		return oldPassword;
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
		if (oldPassword == null || oldPassword.trim().isEmpty()) {
			errors.set(0, "Old Password is required!");
			complete = false;
		} else if (!CommonUtil.isLegal(oldPassword)) {
			errors.set(0, "Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}

		if (newPassword == null || newPassword.trim().isEmpty()) {
			errors.set(1, "New Password is required!");
			complete = false;

		} else if(!CommonUtil.isLegal(newPassword)){
			errors.set(1, "Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}

		if (checkPassword == null || checkPassword.trim().isEmpty()) {
			errors.set(2, "Please comfirm password!");
			complete = false;
		} else if(!CommonUtil.isLegal(checkPassword)){
			errors.set(2, "Invalid input : contains special character or too long (maximum 40).");
			complete = false;
		}

		if (complete == true) {
			if (!newPassword.trim().equals(checkPassword.trim())) {
				errors.set(1,
						"Please make sure the password you re-enter is correct!");
				complete = false;
			}
		}
	}

}

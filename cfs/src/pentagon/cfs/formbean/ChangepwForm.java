package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ChangepwForm {
	private String oldPassword;
	private String newPassword;
	private String checkPassword;
	private boolean complete = true;
	private ArrayList<String> errors;

	public String getOldPassword() {
		return oldPassword;
	}

	public ChangepwForm(HttpServletRequest request) {
		this.oldPassword = (String) request.getParameter("OldPassword");
		this.newPassword = (String) request.getParameter("NewPassword");
		this.checkPassword = (String) request.getParameter("CheckPassword");
		int size = 3;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public String getnewPassword() {
		return newPassword;
	}

	public String getcheckPassword() {
		return checkPassword;
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

		}

		if (newPassword == null || newPassword.trim().isEmpty()) {
			errors.set(1, "New Password is required!");
			complete = false;

		}

		if (checkPassword == null || checkPassword.trim().isEmpty()) {
			errors.set(2, "Please comfirm password!");
			complete = false;
		}

		if (complete == true) {
			if (!newPassword.trim().equals(checkPassword.trim())) {
				errors.set(2,
						"Please make sure the password you re-enter is correct!");
				complete = false;
			}
		}
	}

}

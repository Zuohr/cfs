package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

public class ChangepwForm {
	private String newPassword;
	private String checkPassword;
	private boolean complete = true;
	private ArrayList<String> errors;

	public ChangepwForm(HttpServletRequest request) {

		this.newPassword = (String) request.getParameter("NewPassword");
		this.checkPassword = (String) request.getParameter("CheckPassword");

		int size = 2;
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
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.trim().isEmpty()) {
			errors.set(0, "New Password is required!");
			complete = false;
		}

		if (checkPassword == null || checkPassword.trim().isEmpty()) {
			errors.set(1, "Please comfirm password!");
			complete = false;
		}

		if (complete = true) {
			if (!newPassword.trim().equals(checkPassword.trim())) {
				errors.set(1, "Please make sure the password you re-enter is correct!");
				complete = false;
			}
		}

		
	}

}

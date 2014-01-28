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

public class CmLoginForm {

	private String userName;
	private String password;
	private boolean complete = true;
	private ArrayList<String> errors;

	public CmLoginForm(HttpServletRequest request) {
		userName = (String) request.getParameter("userName");
		password = (String) request.getParameter("password");

		int size = 2;
		errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkError();
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public boolean isComplete() {
		return complete;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void checkError() {

		if (userName == null || userName.trim().isEmpty()) {
			errors.set(0, "User name cannot be empty.");
			complete = false;
		}else if (!CommonUtil.isLegal(userName)) {
			errors.set(0, "Can not contain special characters or input is too long.");
			complete = false;
		}

		if (password == null || password.trim().isEmpty()) {
			errors.set(1, "Password cannot be empty.");
			complete = false;
		}else if (!CommonUtil.isLegal(password)) {
			errors.set(1, "Can not contain special characters or input is too long.");
			complete = false;
		}
	}
}

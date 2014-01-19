/*
 * Name: Haoran Zuo
 * Date: 1 Dec 2013
 * Andrew ID: hzuo
 * Course: 08-600
 */

package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	private String email;
	private String password;
	private boolean complete = true;
	private ArrayList<String> errors;

	public LoginForm(HttpServletRequest request) {
		this.email = (String) request.getParameter("email");
		this.password = (String) request.getParameter("password");
		int size = 2;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkErrors();
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public boolean isComplete() {
		return complete;
	}

	public String getEmail() {
		if (email == null) {
			return "";
		} else {
			return email;
		}
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		if (password == null) {
			return "";
		} else {
			return password;
		}
	}

	private void checkErrors() {
		if (email == null || "".equals(email.trim())) {
			errors.set(0, "email can not be empty");
			complete = false;
		} else if (email.length() > 40) {
			errors.set(0, "length of email can not exceed 40.");
			complete = false;
		}

		if (password == null || "".equals(password.trim())) {
			errors.set(1, "password can not be empty");
			complete = false;
		} else if (password.length() > 32) {
			errors.set(1, "length of password can not exceed 32.");
			complete = false;
		}
	}
}

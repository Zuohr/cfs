package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ResetpwForm {
	public class LoginForm {

		private String newPw;
		private String newPwConfirm;
		private boolean complete = true;
		private ArrayList<String> errors;

		public LoginForm(HttpServletRequest request) {

			this.newPw = (String) request.getParameter("newPw");
			this.newPwConfirm = (String) request.getParameter("newPwConfirm");
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

		private void checkErrors() {
			if (newPw == null || "".equals(newPw.trim())) {
				errors.set(0, "Password can not be empty");
				complete = false;
			} else if (newPwConfirm.length() > 18) {
				errors.set(0, "Length of Password can not exceed 18 digits.");
				complete = false;
			}

			if (newPwConfirm == null || "".equals(newPwConfirm.trim())) {
				errors.set(1, "Password confirm can not be empty");
				complete = false;
			} else if (newPw != newPwConfirm) {
				errors.set(1, "Please confirm the right new password.");
				complete = false;
			}

		}
	}
}

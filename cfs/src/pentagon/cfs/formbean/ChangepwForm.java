package pentagon.cfs.formbean;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

public class ChangepwForm{
	private String oldPassword;
	private String newPassword;
	private String checkPassword;	
	private boolean complete = true;			
	private ArrayList<String> errors;

	public ChangepwForm(HttpServletRequest request) {		
		this.oldPassword = (String) request.getParameter("OldPassword");
		this.newPassword = (String) request.getParameter("NewPassword");
		this.checkPassword = (String) request.getParameter("CheckPassword");
		
		int size = 2;
		this.errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		getValidationErrors();
	}
	public String getoldPassword()   { return oldPassword; }
	public String getnewPassword()   { return newPassword; }
	public String getcheckPassword() { return checkPassword; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (oldPassword == null || oldPassword.length() == 0) {
			errors.add("Old Password is required");
			complete = false;
		}
		
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
			complete = false;
		}
		
		if (checkPassword == null || checkPassword.length() == 0) {
			errors.add("Check Password is required");
			complete = false;
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(checkPassword)) {
			errors.add("New Passwords do not match");
		}

		return errors;
	}
	

	public boolean isComplete() {
		return complete;
	}

}








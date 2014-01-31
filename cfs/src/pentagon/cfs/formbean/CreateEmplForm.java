package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.Employee;
import pentagon.cfs.model.CommonUtil;

public class CreateEmplForm {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String password2;
	private boolean complete = true;
	private ArrayList<String> errors;
	
	public CreateEmplForm(HttpServletRequest request) {
		firstName = (String) request.getParameter("firstName");
		lastName = (String) request.getParameter("lastName");
		userName = (String) request.getParameter("userName");
		password = (String) request.getParameter("password");
		password2 = (String) request.getParameter("password2");

		int size = 5;
		errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkError();
	}
	
	public Employee getEmployeeBean() {
		Employee empl = new Employee();
		
		empl.setFirstname(firstName);
		empl.setLastname(lastName);
		empl.setUsername(userName);
		empl.setPassword(password);
		
		return empl;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public ArrayList<String> getErrors() {
		return errors;
	}

	private void checkError() {
		if (firstName == null || firstName.trim().isEmpty()) {
			errors.set(0, "First name cannot be empty.");
			complete = false;
		}else if (!CommonUtil.isLegal(firstName)) {
			errors.set(0, "Invalid input : contains special character or too long (maximum 30).");
			complete = false;
		}

		if (lastName == null || lastName.trim().isEmpty()) {
			errors.set(1, "Last name cannot be empty.");
			complete = false;
		}else if (!CommonUtil.isLegal(lastName)) {
			errors.set(1, "Invalid input : contains special character or too long (maximum 30).");
			complete = false;
		}

		if (userName == null || userName.trim().isEmpty()) {
			errors.set(2, "User name cannot be empty.");
			complete = false;
		}else if (!CommonUtil.isLegal(userName)) {
			errors.set(2, "Invalid input : contains special character or too long (maximum 30).");
			complete = false;
		}

		if (password == null || password.trim().isEmpty()) {
			errors.set(3, "Please enter password.");
			complete = false;
		}else if (!CommonUtil.isLegal(password)) {
			errors.set(3, "Invalid input : contains special character or too long (maximum 30).");
			complete = false;
		}
		
		if (password2 == null || password2.trim().isEmpty()) {
			errors.set(4, "Please confirm password.");
			complete = false;
		}else if (!CommonUtil.isLegal(password2)) {
			errors.set(4, "Invalid input : contains special character or too long (maximum 30).");
			complete = false;
		}
		
		if (!password.equals(password2)) {
			errors.set(4, "Password does not match.");
			complete = false;
		}
	}
}

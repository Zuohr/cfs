package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.Employee;

public class CreateEmplForm {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String password2;
	boolean complete = true;
	ArrayList<String> errors;
	
	public CreateEmplForm(HttpServletRequest request) {
		firstName = (String) request.getParameter("firstName");
		lastName = (String) request.getParameter("lastName");
		userName = (String) request.getParameter("userName");
		password = (String) request.getParameter("password");
		password2 = (String) request.getParameter("password2");

		int size = 4;
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
		}

		if (lastName == null || lastName.trim().isEmpty()) {
			errors.set(1, "Last name cannot be empty.");
			complete = false;
		}

		if (userName == null || userName.trim().isEmpty()) {
			errors.set(2, "User name cannot be empty.");
			complete = false;
		}

		if (password == null || password2 == null || password.trim().isEmpty()
				|| password2.trim().isEmpty()) {
			errors.set(3, "Password cannot be empty.");
			complete = false;
		} else if (!password.equals(password2)) {
			errors.set(3, "Password does not match.");
			complete = false;
		}
	}
}

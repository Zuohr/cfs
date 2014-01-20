/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.Customer;

public class CreateCmForm {
	private String firstName;
	private String lastName;
	private String userName;
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String zip;
	private String password;
	private String password2;
	private boolean complete = true;
	private ArrayList<String> errors;

	public CreateCmForm(HttpServletRequest request) {
		firstName = (String) request.getParameter("firstName");
		lastName = (String) request.getParameter("lastName");
		userName = (String) request.getParameter("userName");
		addr1 = (String) request.getParameter("addr1");
		addr2 = (String) request.getParameter("addr2");
		city = (String) request.getParameter("city");
		state = (String) request.getParameter("state");
		zip = (String) request.getParameter("zip");
		password = (String) request.getParameter("password");
		password2 = (String) request.getParameter("password2");

		int size = 7;
		errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		checkError();
	}

	public Customer getCustomerBean() {
		Customer cm = new Customer();

		cm.setFirstname(firstName);
		cm.setLastname(lastName);
		cm.setUsername(userName);
		cm.setAddr1(addr1);
		cm.setAddr2(addr2);
		cm.setCity(city);
		cm.setState(state);
		cm.setZip(zip);
		cm.setPassword(password);

		return cm;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public boolean isComplete() {
		return complete;
	}

	public void checkError() {
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

		if (addr1 == null || addr1.trim().isEmpty()) {
			errors.set(3, "Address line 1 cannot be empty.");
			complete = false;
		}

		if (city == null || city.trim().isEmpty()) {
			errors.set(4, "City cannot be empty.");
			complete = false;
		}

		if (zip == null || zip.trim().isEmpty()) {
			errors.set(5, "Zip cannot be empty.");
			complete = false;
		} else if (!zip.matches("\\d{5}")) {
			errors.set(5, "Zip format should be 5 digits.");
			complete = false;
		}

		if (password == null || password2 == null || password.trim().isEmpty()
				|| password2.trim().isEmpty()) {
			errors.set(6, "Password cannot be empty.");
			complete = false;
		} else if (!password.equals(password2)) {
			errors.set(6, "Password does not match.");
			complete = false;
		}
	}
}

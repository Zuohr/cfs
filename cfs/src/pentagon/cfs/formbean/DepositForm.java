package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DepositForm {

	private long deposit;

	public long getDeposit() {
		return deposit;
	}

	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}

	private String userName;
	private int id;
	private String de;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public DepositForm(HttpServletRequest request) {
		de = request.getParameter("deposit").trim();
		if (!de.isEmpty()) {
			deposit = Long.parseLong(request.getParameter("deposit"));
		} else {
			checkErrors();
		}

	}

	public List<String> checkErrors() {
		List<String> errors = new ArrayList<String>();

		if (deposit == 0 || de.isEmpty()) {
			errors.add("Deposit amount is required!");
		}

		if (deposit < 0) {
			errors.add("Deposit amount can not be negitive!");
		}
		return errors;
	}
}

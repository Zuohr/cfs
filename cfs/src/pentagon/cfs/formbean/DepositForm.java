package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DepositForm {
	
	private long deposit;
	private String userName;
	private int id;




	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public long getDeposit() {
		return deposit;
	}



	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}





	public DepositForm(HttpServletRequest request) {
		deposit = Long.parseLong(request.getParameter("deposit"));
		
	}



	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (deposit == 0) {
			errors.add("Deposit amount is required!");
		}
		if (deposit< 0) {
			errors.add("Deposit amount can not be negitive!");
		}
		return errors;
	}
}

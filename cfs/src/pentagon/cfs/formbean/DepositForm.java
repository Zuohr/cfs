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

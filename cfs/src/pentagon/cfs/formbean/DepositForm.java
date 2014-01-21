package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DepositForm {
	private String amount;
	private long checkamount;

	public DepositForm(HttpServletRequest request) {
		amount = request.getParameter("checkamount");
		checkamount = Long.parseLong(amount);
	}
	    public long getAmount()  { return checkamount; }
		
		public List<String> getValidationErrors() {
			List<String> errors = new ArrayList<String>();
	
			if (checkamount==0) {
				errors.add("Amount is required");
			}
						
			return errors;
		}
	}
	
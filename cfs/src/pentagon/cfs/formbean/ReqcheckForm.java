package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class reqcheckForm {
	private String amount;
	private long reqamount;

	public reqcheckForm(HttpServletRequest request) {
		amount = request.getParameter("requestamount");
		reqamount = Long.parseLong(amount);
	}
	    public long getAmount()  { return reqamount; }
		
		public List<String> getValidationErrors() {
			List<String> errors = new ArrayList<String>();
	
			if (reqamount==0) {
				errors.add("Amount is required");
			}
						
			return errors;
		}
	}
	
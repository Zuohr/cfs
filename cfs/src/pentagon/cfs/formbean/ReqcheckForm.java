package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ReqcheckForm {
private long check;
	


	public long getCheck() {
		return check;
	}



	public void setCheck(long check) {
		this.check = check;
	}

	public ReqcheckForm(HttpServletRequest request) {
         check = Long.parseLong(request.getParameter("check"));
		
	}



		
		public List<String> getValidationErrors() {
			List<String> errors = new ArrayList<String>();
	
		

			if (check == 0) {
				errors.add("Check amount is required!");
			}
			if (check< 0) {
				errors.add("Check amount can not be negitive!");
			}
			return errors;
		}
	}
	
package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ReqcheckForm {
	private long check;
	private String ch;
	private ArrayList<String> errors;
	private boolean complete = true;

	public boolean isComplete() {
		return complete;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public ReqcheckForm(HttpServletRequest request) {
		ch = request.getParameter("check");
		errors = new ArrayList<String>();
		checkErrors();
	}

	public long getCheck() {
		return check;
	}

	public void setCheck(long check) {
		this.check = check;
	}

	public void checkErrors() {
		if (ch == null || ch.isEmpty()) {
			errors.add("Value cannot be empty.");
			complete = false;
		} else {
			try {
				check = (long) Double.parseDouble(ch);
				if (check <= 0) {
					errors.add("Invalid value.");
					complete = false;
				}
			} catch (NumberFormatException e) {
				errors.add("Invalid value.");
				complete = false;
			}
		}

	}
}

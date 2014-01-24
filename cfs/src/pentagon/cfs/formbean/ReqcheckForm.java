package pentagon.cfs.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ReqcheckForm {
	private Double check;
	private String ch;

	public ReqcheckForm(HttpServletRequest request) {
		ch = request.getParameter("check").trim();
		if (!ch.isEmpty()) {
			check = Double.parseDouble(request.getParameter("check"));
		} else {
			checkErrors();
		}

	}

	public Double getCheck() {
		return check;
	}

	public void setCheck(Double check) {
		this.check = check;
	}

	public List<String> checkErrors() {
		List<String> errors = new ArrayList<String>();

		if (check == 0||ch.isEmpty()) {
			errors.add("Check amount is required!");
		}
		if (check < 0) {
			errors.add("Check amount can not be negitive!");
		}
		return errors;
	}
}

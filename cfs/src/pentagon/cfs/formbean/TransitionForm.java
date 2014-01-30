/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.formbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.model.CommonUtil;

public class TransitionForm {
	private String dateInput;
	private Date date;
	private int fundnum;
	private boolean complete = true;
	private ArrayList<String> errors;
	private ArrayList<FundPriceHistory> priceList;

	public TransitionForm(HttpServletRequest request) {
		dateInput = (String) request.getParameter("date");
		String num = (String) request.getParameter("fund_num");
		fundnum = Integer.parseInt(num);
		int size = fundnum + 1;
		errors = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			errors.add("");
		}
		priceList = new ArrayList<FundPriceHistory>();
		for (int i = 0; i < fundnum; i++) {
			priceList.add(new FundPriceHistory());
		}
		checkErrors(request);
	}

	public ArrayList<FundPriceHistory> getFundPrices() {
		return priceList;
	}

	public boolean isComplete() {
		return complete;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public Date getDate() {
		return date;
	}

	private void checkErrors(HttpServletRequest request) {
		if (dateInput == null || dateInput.trim().isEmpty()) {
			errors.set(0, "Date cannot be empty.");
			complete = false;
		} else if (!dateInput.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
			errors.set(0, "Date format should be mm/dd/yyyy.");
			complete = false;
		} else {
			SimpleDateFormat f = new SimpleDateFormat(Meta.DATE_FORMAT);
			f.setLenient(false);
			try {
				date = f.parse(dateInput);
				if (Meta.lastDate != null) {
					Date lastDate = f.parse(Meta.lastDate);
					if (lastDate.after(date) || lastDate.equals(date)) {
						errors.set(0,
								"Transition day should be later than last trading day("
										+ Meta.lastDate + ").");
						complete = false;
					} else {
					}
				}
			} catch (ParseException e) {
				errors.set(0, "Invalid date.");
				complete = false;
			}
		}

		for (int i = 1; i <= fundnum; i++) {
			String priceInput = (String) request.getParameter("price_" + i);
			long price = 0;
			try {
				price = CommonUtil.getNumber(priceInput, 2);
				FundPriceHistory fp = priceList.get(i - 1);
				fp.setFund_id(i);
				fp.setDate(date);
				fp.setPrice(price);
			} catch (RuntimeException e) {
				errors.set(i, e.getMessage());
				complete = false;
			}
		}
	}
}

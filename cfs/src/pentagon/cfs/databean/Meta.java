package pentagon.cfs.databean;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Meta {
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static String lastDate = new SimpleDateFormat(DATE_FORMAT)
			.format(new Date());
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

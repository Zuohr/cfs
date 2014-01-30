package pentagon.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public final class Meta {
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final int CASH_PRECISION = 2;
	public static final int SHARE_PRECISION = 3;
	public static String lastDate = null;
	private String date;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

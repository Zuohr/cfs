/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.databean;

public class PositionRecord {
	private int fundId;
	private String fundName;
	private String share;
	private String shareBalance;
	private String lastPrice;
	private String value;

	public PositionRecord(String fundName, long share, long shareBalance,
			long lastPrice) {
		this.fundName = fundName;
		this.share = String.format("%.3f", (double) share / 1000);
		this.shareBalance = String.format("%.3f", (double) shareBalance / 1000);
		this.lastPrice = String.format("%.2f", (double) lastPrice / 100);
		this.value = String.format("%.2f", ((double) share / 1000)
				* ((double) lastPrice / 100));
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public String getShare() {
		return share;
	}

	public String getShareBalance() {
		return shareBalance;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getValue() {
		return value;
	}
}

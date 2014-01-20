package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class SellForm {
	private String fundName;
	private long fundAmount;
	private boolean complete = true;
	private ArrayList<String> errors;
	
	public SellForm(HttpServletRequest request){
		this.fundName = (String) request.getParameter("fundName");
		this.fundAmount = Long.parseLong(request.getParameter("fundAmount"));
		int size = 1;
		this.errors = new ArrayList<String>(size);
		for(int i=0; i<size; i++){
			errors.add("");
		}
		checkErrors();
	}
	
	public ArrayList<String> getErrors(){
		return errors;
	}
	
	public boolean isComplete(){
		return complete;
	}
	
	public String getFundName(){
		if(fundName==null) return "";
		else return fundName;
	}
	
	public long getFundAmount(){
		return fundAmount;
	}
	
	public void setFundAmount(long num){
		fundAmount = num;
	}
	private void checkErrors(){
		if(fundAmount<0){
			errors.set(0, "Number of shares cannot be negative.");
			complete = false;
		}
	}
}

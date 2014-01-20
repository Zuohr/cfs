package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class BuyForm {
	private String fundName;
	private long deposit;
	private boolean complete = true;
	private ArrayList<String> errors;
	
	public BuyForm(HttpServletRequest request){
		this.fundName = (String)request.getParameter("fundName");
		this.deposit = Long.parseLong(request.getParameter("deposit"));
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
	
	public long getDeposit(){
		return deposit;
	}
	
	public void setDeposit(long num){
		deposit = num;
	}
	
	private void checkErrors(){
		if(deposit<0){
			errors.set(0, "Amount of deposit cannot be negative.");
			complete = false;
		}
	}
}


package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.TransactionRecord;

public class BuyForm {
	private int fundId;
	private double deposit;
	private boolean complete = true;
	private ArrayList<String> errors;
	
	public BuyForm(HttpServletRequest request){
		this.fundId = Integer.parseInt(request.getParameter("fundId"));
		
		int size = 2;
		this.errors = new ArrayList<String>(size);
		for(int i=0; i<size; i++){
			errors.add("");
		}
		if(!request.getParameter("deposit").trim().isEmpty())
			this.deposit = Double.parseDouble(request.getParameter("deposit"));
		else{
			errors.set(1, "Please enter some numbers");
			complete = false;
		}
		checkErrors();
	}
	
	public TransactionRecord getBuyFund(){
		TransactionRecord record = new TransactionRecord();
		record.setFund_id(fundId);
		record.setAmount((long)(deposit*100));
		record.setType("buy");
		return record;
	}
	
	public ArrayList<String> getErrors(){
		return errors;
	}
	
	public boolean isComplete(){
		return complete;
	}
	
	public double getDeposit(){
		return deposit;
	}
	
	public void setDeposit(long num){
		deposit = num;
	}
	
	private void checkErrors(){
		if(deposit<0.00){
			errors.set(0, "Amount of deposit cannot be negative.");
			complete = false;
		}
	}
}

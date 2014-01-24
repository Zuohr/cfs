package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import pentagon.cfs.databean.TransactionRecord;

public class SellForm {
	private int fundId;
	private double fundAmount;
	private boolean complete = true;
	private ArrayList<String> errors;
	
	public SellForm(HttpServletRequest request){
		
		this.fundId = Integer.parseInt(request.getParameter("fundId"));
		int size = 2;
		this.errors = new ArrayList<String>(size);
		for(int i=0; i<size; i++){
			errors.add("");
		}
		if(!request.getParameter("sellAmount").trim().isEmpty()){
			this.fundAmount = Double.parseDouble(request.getParameter("sellAmount"));
		} else{
			errors.set(1, "Please enter some numbers");
			complete = false;
		}
		checkErrors();
	}
	
	public TransactionRecord getSellFund(){
		TransactionRecord record = new TransactionRecord();
		record.setFund_id(fundId);
		record.setAmount((long)(fundAmount*1000));
		record.setType("sell");
		return record;
	}
	
	public ArrayList<String> getErrors(){
		return errors;
	}
	
	public boolean isComplete(){
		return complete;
	}
	
	public int getFundId(){
		return fundId;
	}
	
	public double getFundAmount(){
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

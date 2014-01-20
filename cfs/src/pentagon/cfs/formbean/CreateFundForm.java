package pentagon.cfs.formbean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class CreateFundForm {
	private String fundName;
	private String fundTicker;
	private boolean complete = true;
	private ArrayList<String> errors;
	
	public CreateFundForm(HttpServletRequest request){
		this.fundName = (String) request.getParameter("fundName");
		this.fundTicker = (String) request.getParameter("fundTicker");
		int size = 2;
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
		return fundName;
	}
	public void setFundName(String name){
		fundName = name;
	}
	public void getFundTicker(String ticker){
		fundTicker = ticker;
	}
	private void checkErrors(){
		if(fundName == null||fundName.trim().isEmpty()){
			errors.set(0, "Fund name cannot be empty.");
			complete = false;
		}
		if(fundTicker==null||fundTicker.trim().isEmpty()){
			errors.set(0, "Fund ticker cannot be empty");
			complete = false;
		}
	}
	
}

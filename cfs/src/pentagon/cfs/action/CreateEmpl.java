package pentagon.cfs.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.model.*;
import pentagon.cfs.dao.*;
import pentagon.cfs.formbean.CreateEmplForm;

public class CreateEmpl implements Action {
	private EmployeeDAO employeeDAO;

	public CreateEmpl(Model model) {
		employeeDAO = model.getEmployeeDAO();

	}	

	
	@Override
	public String perform(HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
   		CreateEmplForm form = new CreateEmplForm(request);
   		if(form.getErrors().size()!=0) {
   			errors.addAll(form.getErrors());
   			return "ee_createcm.jsp";
   		}else{
   			try {
				employeeDAO.create(form.getEmployeeBean());
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   			
   		}
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "createEmpl.do";
	}

}

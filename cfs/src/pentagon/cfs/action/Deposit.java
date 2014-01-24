//wc
package pentagon.cfs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import pentagon.cfs.dao.CustomerDAO;
import pentagon.cfs.dao.TransactionDAO;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.TransactionRecord;
import pentagon.cfs.formbean.DepositForm;
import pentagon.cfs.model.Model;

public class Deposit implements Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public Deposit(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "deposit.do";
	}

	@Override
	public String perform(HttpServletRequest request) throws RollbackException {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		Employee empolyee = (Employee) request.getSession().getAttribute("empolyee");

		if (empolyee == null) {
			return "login.do";
		} else {

			if ("submit".equals(request.getParameter("deposit_btn"))) {
				DepositForm form = new DepositForm(request);
				int id = Integer.parseInt(request.getParameter("id"));

				long deposit = form.getDeposit();

				TransactionRecord record = new TransactionRecord();
				record.setCm_id(id);
				record.setAmount(deposit);
				record.setComplete(false);
				record.setType("deposit"); // need to complete

				try {
					transactionDAO.create(record);

					request.setAttribute("errors", "Add " + form.getDeposit()
							+ "to " + form.getUserName() + "  successfully!");
					return "ee_depositcheck.jsp";
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "ee_depositcheck.jsp";
				}

			}else{
				return "ee_depositcheck.jsp";
			}

		}
		

	}
}
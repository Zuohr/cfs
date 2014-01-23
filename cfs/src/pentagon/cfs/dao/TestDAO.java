package pentagon.cfs.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.junit.Test;

import pentagon.cfs.databean.Customer;
import pentagon.cfs.databean.Employee;
import pentagon.cfs.databean.Fund;
import pentagon.cfs.databean.FundPriceHistory;
import pentagon.cfs.databean.Meta;
import pentagon.cfs.databean.Position;
import pentagon.cfs.databean.TransactionRecord;

public class TestDAO {
	private String jdbcName = "com.mysql.jdbc.Driver";
	private String jdbcURL = "jdbc:mysql:///cfs";

	private ConnectionPool getConnectionPool() {
		return new ConnectionPool(jdbcName, jdbcURL);
	}

	@Test
	public void testCmDAO() throws DAOException {
		ConnectionPool cp = getConnectionPool();
		CustomerDAO cmdao = new CustomerDAO("cfs_customer", cp);
		Customer cm = new Customer();
		cm.setUsername("username2");
		cm.setPassword("psw");
		// cm.setFirstname("first");
		// cm.setLastname("last");
		cm.setAddr1("addr1");
		cm.setAddr2("addr2");
		cm.setCity("city");
		cm.setState("state");
		cm.setZip("zip");
		cm.setCash(100);
		cm.setBalance(50);
		cm.setLasttrading(new Date());
		cm.setLoggedin(true);

		try {
			Transaction.begin();
			System.out.println(cmdao.createCustomer(cm));
			System.out.println(cmdao.createCustomer(cm));
			System.out.println(cmdao.createCustomer(cm));
			Customer cm2 = cmdao.getProfile(cm.getUsername());
			System.out.println(cm2.getFirstname() == null);
			System.out.println(cm2.getLastname() == null);
			System.out.println(cm2);
			cm2.setAddr1("new addr");
			cmdao.updateCustomer(cm2);
			System.out.println("***");
			Customer[] cms = cmdao.getAll();
			for (Customer c : cms) {
				System.out.println(c);
			}
			Transaction.commit();
		} catch (RollbackException e) {
			System.out.println("roll back exception");
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}

	@Test
	public void testEmplDAO() throws DAOException {
		ConnectionPool cp = getConnectionPool();
		EmployeeDAO dao = new EmployeeDAO("cfs_empl", cp);
		Employee e = new Employee();
		e.setFirstname("first");
		e.setLastname("last");
		e.setUsername("username");
		e.setPassword("password");

		try {
			System.out.println(dao.createEmployee(e));
			System.out.println(dao.createEmployee(e));
			System.out.println(dao.createEmployee(e));
			Employee e2 = dao.getProfile(e.getUsername());
			System.out.println(e2);
			e2.setPassword("new psd");
			dao.updateEmployee(e2);
			e2 = dao.getProfile(e2.getUsername());
			System.out.println(e2);
		} catch (RollbackException e1) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}

	@Test
	public void testTransDAO() throws DAOException {
		ConnectionPool cp = getConnectionPool();
		TransactionDAO dao = new TransactionDAO("cfs_transaction", cp);

		for (int i = 1; i <= 5; i++) {
			TransactionRecord tr = new TransactionRecord();
			tr.setCm_id(i);
			tr.setFund_id(i);
			tr.setDate(new Date());
			tr.setShare(10);
			tr.setComplete(false);
			tr.setType("sell");
			try {
				dao.create(tr);
			} catch (RollbackException e) {
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= 5; i++) {
			TransactionRecord tr = new TransactionRecord();
			tr.setCm_id(i);
			tr.setFund_id(i);
			tr.setDate(new Date());
			tr.setAmount(100);
			tr.setComplete(false);
			tr.setType("buy");
			try {
				dao.create(tr);
			} catch (RollbackException e) {
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= 5; i++) {
			TransactionRecord tr = new TransactionRecord();
			tr.setCm_id(i);
			tr.setDate(new Date());
			tr.setAmount(100);
			tr.setComplete(false);
			tr.setType("deposit");
			try {
				dao.create(tr);
			} catch (RollbackException e) {
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= 5; i++) {
			TransactionRecord tr = new TransactionRecord();
			tr.setCm_id(i);
			tr.setDate(new Date());
			tr.setAmount(100);
			tr.setComplete(false);
			tr.setType("withdraw");
			try {
				dao.create(tr);
			} catch (RollbackException e) {
				e.printStackTrace();
			}
		}

		try {
			TransactionRecord[] pending = dao.getPending();
			System.out.println(pending.length);
			for (TransactionRecord tr : pending) {
				System.out.println(tr.getDate());
				tr.setComplete(true);
				dao.update(tr);
			}
			pending = dao.getPending();
			System.out.println(pending.length);
			TransactionRecord[] history = dao.getHistory(9);
			System.out.println(history.length);
			for (TransactionRecord tr : history) {
				System.out.println(tr.getType());
			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPosDAO() throws DAOException {
		ConnectionPool cp = getConnectionPool();
		PositionDAO dao = new PositionDAO("cfs_pos", cp);
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j <= 3; j++) {
				Position p = new Position();
				p.setCm_id(i);
				p.setFund_id(j);
				p.setShare(1);
				try {
					dao.create(p);
					;
				} catch (RollbackException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			Position[] ps = dao.getPositions(1);
			for (Position p : ps) {
				System.out.println(p.getShare());
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testFundDAO() throws DAOException {
		ConnectionPool cp = getConnectionPool();
		FundDAO dao = new FundDAO("cfs_fund", cp);
		for (int i = 0; i < 5; i++) {
			Fund f = new Fund();
			f.setName("fund" + i);
			f.setSymbol("FD" + i);
			try {
				System.out.println(dao.createFund(f));
				System.out.println(dao.getCount());
			} catch (RollbackException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testFundPriceHisDAO() throws DAOException, RollbackException {
		ConnectionPool cp = getConnectionPool();
		FundPriceHistoryDAO dao = new FundPriceHistoryDAO("cfs_fundprice", cp);
		for (int i = 1; i < 5; i++) {
			FundPriceHistory fp = new FundPriceHistory();
			fp.setFund_id(i);
			fp.setDate(new Date());
			fp.setPrice(Math.abs(new Random().nextLong() % 1000));
			dao.create(fp);
		}
		FundPriceHistory[] fps = dao.getHistory(1);
		for (FundPriceHistory fp : fps) {
			System.out.println(fp.getPrice());
		}
	}
	
	@Test
	public void testMetaDAO() throws DAOException, RollbackException {
		ConnectionPool cp = getConnectionPool();
		MetaDAO dao = new MetaDAO("cfs_meta", cp);
		Meta meta = new Meta();
		meta.setDate(new SimpleDateFormat(Meta.DATE_FORMAT).format(new Date()));
		dao.create(meta);
		String date = dao.read(Integer.valueOf(1)).getDate();
		System.out.println(date);
		meta.setId(1);
		meta.setDate("aa");
		dao.update(meta);
		date = dao.read(Integer.valueOf(1)).getDate();
		System.out.println(date);
	}
}

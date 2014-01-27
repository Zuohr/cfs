/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;

import pentagon.cfs.action.Action;
import pentagon.cfs.action.ActionMap;
import pentagon.cfs.action.BuyFund;
import pentagon.cfs.action.CmChangePw;
import pentagon.cfs.action.CmLogin;
import pentagon.cfs.action.CmViewAcct;
import pentagon.cfs.action.CmViewTranHistory;
import pentagon.cfs.action.CreateCustomer;
import pentagon.cfs.action.CreateEmpl;
import pentagon.cfs.action.CreateFund;
import pentagon.cfs.action.Deposit;
import pentagon.cfs.action.EmplChangePw;
import pentagon.cfs.action.EmplLogin;
import pentagon.cfs.action.EmplViewAcct;
import pentagon.cfs.action.EmplViewCmList;
import pentagon.cfs.action.EmplViewTranHistroy;
import pentagon.cfs.action.Logout;
import pentagon.cfs.action.RequestCheck;
import pentagon.cfs.action.ResearchFund;
import pentagon.cfs.action.ResetCmPw;
import pentagon.cfs.action.SellFund;
import pentagon.cfs.action.TransitionDay;
import pentagon.cfs.model.Model;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "*.do" }, initParams = {
		@WebInitParam(name = "jdbcName", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "jdbcURL", value = "jdbc:mysql:///cfs") })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Model model;
	private ActionMap actions;
	private final String jspPath = "/WEB-INF/";

	@Override
	public void init() throws ServletException {
		model = new Model(getServletConfig());
		actions = new ActionMap();
		// add actions
		actions.addAction(new BuyFund(model));
		actions.addAction(new CmChangePw(model));
		actions.addAction(new CmLogin(model));
		actions.addAction(new CmViewAcct(model));
		actions.addAction(new CmViewTranHistory(model));
		actions.addAction(new CreateCustomer(model));
		actions.addAction(new CreateEmpl(model));
		actions.addAction(new CreateFund(model));
		actions.addAction(new Deposit(model));
		actions.addAction(new EmplChangePw(model));
		actions.addAction(new EmplLogin(model));
		actions.addAction(new EmplViewAcct(model));
		actions.addAction(new EmplViewCmList(model));
		actions.addAction(new EmplViewTranHistroy(model));
		actions.addAction(new Logout());
		actions.addAction(new RequestCheck(model));
		actions.addAction(new ResearchFund(model));
		actions.addAction(new ResetCmPw(model));
		actions.addAction(new SellFund(model));
		actions.addAction(new TransitionDay(model));
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nextStep;
		try {
			nextStep = processRequest(request);
			proceedToNext(nextStep, request, response);
		} catch (RollbackException e) {
			throw new RuntimeException(e.getCause());
		}

	}

	private String processRequest(HttpServletRequest request)
			throws RollbackException {
		String actionName = getActionName(request.getServletPath());
		Action a = actions.getAction(actionName);
		if (a == null) {
			return "404";
		} else {
			return a.perform(request);
		}
	}

	private void proceedToNext(String nextStep, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextStep.endsWith(".do")) {
			response.sendRedirect(nextStep);
		} else if (nextStep.endsWith(".jsp")) {
			request.getRequestDispatcher(jspPath + nextStep).forward(request,
					response);
		} else if ("404".equals(nextStep)) {
			response.sendError(404);
		} else {
			response.sendRedirect(nextStep);
		}
	}

	private String getActionName(String path) {
		return path.substring(path.lastIndexOf('/') + 1);
	}
}

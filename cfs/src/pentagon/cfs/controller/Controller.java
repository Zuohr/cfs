/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pentagon.cfs.action.Action;
import pentagon.cfs.action.ActionMap;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nextStep;
		try {
			nextStep = processRequest(request);
			proceedToNext(nextStep, request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	private String processRequest(HttpServletRequest request)
			throws SQLException {
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

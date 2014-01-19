/*
 * Name: Haoran Zuo
 * Date: 1 Dec 2013
 * Andrew ID: hzuo
 * Course: 08-600
 */

package pentagon.cfs.formbean;

import hzuo.hw9.databean.UserBean;
import hzuo.hw9.formbean.LoginForm;
import hzuo.hw9.model.Model;
import hzuo.hw9.model.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SigninAction implements Action {

	private Model model;

	public SigninAction(Model model) {
		super();
		this.model = model;
	}

	@Override
	public String perform(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user != null) {
			return "main.do";
		} else {
			if ("Sign in".equals(request.getParameter("button"))) {
				LoginForm form = new LoginForm(request);
				if (form.isComplete()) {
					UserDAO udao = model.getUserDAO();
					UserBean u = udao.read(form.getEmail());
					if (u == null
							|| !form.getPassword().equals(u.getPassword())) {
						request.setAttribute("account_error",
								"incorrect email and password match");
						request.setAttribute("loginForm", form);
						return "signin.jsp";
					} else {
						session.setAttribute("user", u);
						session.removeAttribute("selectedUser");
						return "main.do";
					}
				} else {
					ArrayList<String> errors = form.getErrors();
					request.setAttribute("errors", errors);
					request.setAttribute("loginForm", form);
					return "signin.jsp";
				}
			} else if ("Register".equals(request.getParameter("button"))) {
				return "register.do";
			} else {
				return "signin.jsp";
			}
		}
	}

	@Override
	public String getName() {
		return "signin.do";
	}

}

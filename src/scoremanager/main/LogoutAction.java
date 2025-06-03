package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session=req.getSession();

		if (session.getAttribute("teacher")!=null) {
			session.removeAttribute("teacher");
			return "logout.jsp";
		}

		return "error.jsp";
	}
}
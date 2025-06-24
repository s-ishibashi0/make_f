package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class TestListSubjectExecuteAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		return "/scoremanager/main/test_list_subject.jsp";

	}
}
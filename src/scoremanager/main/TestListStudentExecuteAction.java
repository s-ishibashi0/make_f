package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class TestListStudentExecuteAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		return "/scoremanager/main/test_list_student.jsp";

	}
}
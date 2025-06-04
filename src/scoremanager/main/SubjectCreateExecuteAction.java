package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateExecuteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");

	    String cd = request.getParameter("cd");
	    String name = request.getParameter("name");
	    String schoolCd = "001"; // 固定値で設定（必要に応じて動的に）

	    if (cd == null || cd.isEmpty() || name == null || name.isEmpty()) {
	        request.setAttribute("errorMsg", "科目コードと科目名は必須です。");
	        request.setAttribute("cd", cd);
	        request.setAttribute("name", name);
	        return "subject_create.jsp";
	    }

	    Subject subject = new Subject();
	    subject.setCd(cd);
	    subject.setName(name);

	    // Schoolオブジェクトをセット
	    School school = new School();
	    school.setCd(schoolCd);
	    subject.setSchool(school);

	    try {
	        SubjectDAO dao = new SubjectDAO();
	        dao.insert(subject);

	        System.out.println("登録成功: SCHOOL_CD=" + schoolCd + ", CD=" + cd + ", NAME=" + name);

	        return "subject_create_done.jsp";

	    } catch (Exception e) {
	        request.setAttribute("errorMsg", "登録に失敗しました: " + e.getMessage());
	        request.setAttribute("cd", cd);
	        request.setAttribute("name", name);
	        e.printStackTrace();
	        return "subject_create.jsp";
	    }
	}
}

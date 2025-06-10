package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateExecuteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        if (cd == null || cd.isEmpty() || name == null || name.isEmpty()) {
            request.setAttribute("errorMsg", "科目コードと科目名は必須です。");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "subject_update.jsp";
        }

        String schoolCd = "oom";  // 固定値

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        School school = new School();
        school.setCd(schoolCd);
        subject.setSchool(school);

        try {
            SubjectDAO dao = new SubjectDAO();
            dao.update(subject);

            request.setAttribute("msg", "科目情報を変更しました。");
            return "subject_update_done.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "科目情報の変更に失敗しました: " + e.getMessage());
            return "subject_update.jsp";
        }
    }
}

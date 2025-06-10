package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String schoolCd = "oom";  // 例: 必要に応じて動的に取得
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            request.setAttribute("errorMsg", "科目コードが指定されていません。");
            return "error.jsp"; // 適切なエラーページへ
        }

        School school = new School();
        school.setCd(schoolCd);

        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.get(code, school);

        if (subject == null) {
            request.setAttribute("errorMsg", "指定された科目は存在しません。");
            return "error.jsp";
        }

        // 削除確認画面へ科目情報を渡す
        request.setAttribute("subject", subject);
        return "subject_delete.jsp";  // 削除確認JSPへ遷移
    }
}

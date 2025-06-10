package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // パラメータから科目コードを取得
        String cd = request.getParameter("code");
        if (cd == null || cd.isEmpty()) {
            // エラー処理（例: 一覧画面に戻す）
            return "subject_list.jsp";
        }

        // 学校コードは固定（例：001 または セッションなどから取得）
        String schoolCd = "oom";
        School school = new School();
        school.setCd(schoolCd);

        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.get(cd, school);

        if (subject == null) {
            // 科目が存在しない場合の処理（一覧へ戻すなど）
            return "subject_list.jsp";
        }

        request.setAttribute("subject", subject);

        // 変更画面へ遷移
        return "subject_update.jsp";
    }
}

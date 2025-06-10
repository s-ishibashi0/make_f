package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteExecuteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String schoolCd = "oom";  // 必要に応じて動的に取得してください
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            request.setAttribute("errorMsg", "科目コードが指定されていません。");
            return "error.jsp";  // 適切なエラーページへ
        }

        School school = new School();
        school.setCd(schoolCd);

        Subject subject = new Subject();
        subject.setCd(code);
        subject.setSchool(school);

        SubjectDAO dao = new SubjectDAO();
        dao.delete(subject);

        // 削除完了画面へ遷移（フォワードまたはリダイレクト）
        request.setAttribute("msg", "科目「" + code + "」を削除しました。");
        return "subject_delete_done.jsp";  // 削除完了JSPへ遷移
    }
}

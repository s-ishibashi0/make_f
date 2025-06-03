package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction implements Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            // フォームから送られたパラメータを取得（IDは文字列として扱う）
            String id = req.getParameter("id");
            String password = req.getParameter("password");



            // DAOで認証チェック
            TeacherDAO dao = new TeacherDAO();
            Teacher teacher = dao.search(id, password);

System.out.println("id:" + id);
System.out.println("pass: " + password);

            if (teacher != null) {
                // 認証成功 → セッションに保存してホームへ
                HttpSession session = req.getSession();
                session.setAttribute("teacher", teacher);
                res.sendRedirect(req.getContextPath() + "/scoremanager/main/menu.jsp");
                return null;

            } else {
                // 認証失敗 → エラーメッセージ設定してエラーページへ
                req.setAttribute("error", "ログインIDまたはパスワードが違います。");
                return "/scoremanager/main/error.jsp";
            }
        } catch (Exception e) {
            // ここでNumberFormatExceptionに限定せず広く例外をキャッチしても良いですが
            req.setAttribute("error", "IDの形式が不正です。");
            return "/scoremanager/main/error.jsp";
        }
    }
}

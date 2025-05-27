package scoremanager.main;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // SubjectDAOのインスタンスを作成
        SubjectDAO subjectDAO = new SubjectDAO();

        // すべての科目を取得（学校に依存しない）
        List<Subject> subjectList = subjectDAO.filter();

        // 取得した科目リストをリクエスト属性にセット
        req.setAttribute("subjectList", subjectList);

        // JSPにフォワードして科目リストを表示
        forward(req, resp, "/scoremanager/main/subject_list.jsp");
    }

    // forwardメソッドを定義
    private void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws Exception {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }
}

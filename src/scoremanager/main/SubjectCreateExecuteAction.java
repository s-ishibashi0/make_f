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
        String schoolCd = "oom"; // SCHOOL_CDを固定

        // 入力バリデーション
        if (cd == null || cd.length() != 3 || name == null || name.isEmpty()) {
            request.setAttribute("errorMsg", "科目コードは3文字で入力してください");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "/scoremanager/main/subject_create.jsp";
        }

        // 学校オブジェクトの生成
        School school = new School();
        school.setCd(schoolCd);

        SubjectDAO dao = new SubjectDAO();

        // 重複チェック
        Subject existing = dao.get(cd, school);
        if (existing != null) {
            request.setAttribute("errorMsg", "科目コードが重複しています");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "/scoremanager/main/subject_create.jsp";
        }

        // 登録処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        try {
            dao.insert(subject);
            System.out.println("登録成功: SCHOOL_CD=" + schoolCd + ", CD=" + cd + ", NAME=" + name);
            return "/scoremanager/main/subject_create_done.jsp";
        } catch (Exception e) {
            request.setAttribute("errorMsg", "登録に失敗しました: " + e.getMessage());
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            e.printStackTrace();
            return "/scoremanager/main/subject_create.jsp";
        }
    }
}

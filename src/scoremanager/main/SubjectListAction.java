package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectListAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("SubjectListAction.execute()開始");

        List<Subject> subjectList = null;
        try {
            SubjectDAO dao = new SubjectDAO();
            School school = new School();
            school.setCd("oom");  // データベースのSCHOOL_CDに合わせて固定
            subjectList = dao.filter(school);
            System.out.println("科目取得件数: " + subjectList.size());
        } catch (Exception e) {
            System.out.println("SubjectDAO.filter()で例外発生");
            e.printStackTrace();
        }

        req.setAttribute("subjectList", subjectList);

        System.out.println("SubjectListAction.execute()終了");
        return "/scoremanager/main/subject_list.jsp";
    }
}

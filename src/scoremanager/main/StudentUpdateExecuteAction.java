package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateExecuteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // フォームから送信されたパラメータを取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        boolean isAttend = request.getParameter("is_attend") != null;

        // 学生オブジェクトを作成して値をセット
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setEntYear(entYear);
        student.setAttend(isAttend);

        // データベースを更新
        StudentDAO dao = new StudentDAO();
        dao.update(student);

        // 完了ページへ遷移
        return "/scoremanager/main/student_update_done.jsp";
    }
}

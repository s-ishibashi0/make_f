package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションから Teacher を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            throw new Exception("教員情報が取得できませんでした。ログインし直してください。");
        }

        // 学校コードを取得し、School オブジェクトを作成
        String schoolCd = teacher.getSchool();
        School school = new School();
        school.setCd(schoolCd);

        // 学生番号を取得
        String no = request.getParameter("no");

        // 学生情報を取得
        Student student = new StudentDAO().get(no);
        if (student == null) {
            throw new Exception("指定された学生情報が見つかりませんでした。");
        }

        // クラス番号一覧を取得
        List<String> classList = new ClassNumDAO().filter(school);

        // JSP に渡す
        request.setAttribute("ent_year", student.getEntYear());
        request.setAttribute("no", student.getNo());
        request.setAttribute("name", student.getName());
        request.setAttribute("class_num", student.getClassNum());
        request.setAttribute("is_attend", student.isAttend());
        request.setAttribute("class_list", classList); // ← クラス一覧を渡す

        return "/scoremanager/main/student_update.jsp";
    }
}

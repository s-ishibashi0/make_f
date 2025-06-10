package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateExecuteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // フォームからの入力値を取得
        String entYear = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        // 入力チェック
        if (entYear == null || entYear.isEmpty() ||
            no == null || no.isEmpty() ||
            name == null || name.isEmpty() ||
            classNum == null || classNum.isEmpty()) {

            request.setAttribute("errorMsg", "すべての項目を入力してください。");
            request.setAttribute("ent_year", entYear);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            return "/scoremanager/main/student_create.jsp";
        }

        // 学生オブジェクトの作成
        Student student = new Student();
        student.setEntYear(Integer.parseInt(entYear));
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(true); // 初期値として在学中とする

        // 学校情報をセット（固定値）
        School school = new School();
        school.setCd("oom");
        student.setSchool(school);

        try {
            // データベースに登録
            StudentDAO dao = new StudentDAO();
            dao.save(student);

            System.out.println("学生登録成功: " + no + " " + name);
            return "/scoremanager/main/student_create_done.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "登録に失敗しました: " + e.getMessage());
            request.setAttribute("ent_year", entYear);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            return "/scoremanager/main/student_create.jsp";
        }
    }
}

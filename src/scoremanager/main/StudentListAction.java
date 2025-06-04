package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentListAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        System.out.println("teacher: " + teacher);
        System.out.println("teacher.getSchool(): " + teacher.getSchool());

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null;
        Map<String, String> errors = new HashMap<>();

        // 現在の年を取得して入学年度リストを作成
        LocalDate todayDate = LocalDate.now();
        int year = todayDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // DAOの準備
        StudentDAO sDao = new StudentDAO();
        ClassNumDAO cNumDao = new ClassNumDAO();

        // Schoolオブジェクトを作成
        School school = new School();
        school.setCd(teacher.getSchool());

        System.out.println("学校コード: " + school.getCd());

        // クラス番号一覧を取得
        List<String> classNumList = cNumDao.filter(school);

        // 入学年度のパース
        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "入学年度は数値で入力してください");
            }
        }

        // 出席情報のパース
        if (isAttendStr != null && !isAttendStr.isEmpty()) {
            isAttend = Boolean.parseBoolean(isAttendStr);
        }

        // 学生リストの取得ロジック
        if (entYear != 0 && !classNum.equals("0")) {
            students = sDao.filter(school, entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(school, entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            students = sDao.filter(school, isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            students = sDao.filter(school, isAttend);
        }

        // リクエスト属性にセット
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttendStr);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("errors", errors);

        return "/scoremanager/main/student_list.jsp";

    }
}

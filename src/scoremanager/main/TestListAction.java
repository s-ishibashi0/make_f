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
import bean.Subject;
import bean.Teacher;
import bean.Test;
import bean.TestListStudent;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        School school = new School();
        school.setCd(teacher.getSchool());

        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");
        String studentNo = request.getParameter("f4"); // 学生番号

        int entYear = 0;

        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            errors.put("f1", "入学年度は数値で入力してください");
        }

        List<Student> students = new ArrayList<>();
        List<TestListStudent> tests = new ArrayList<>();

        // 学生一覧取得（入学年度、クラス、科目）
        StudentDAO sDao = new StudentDAO();
        if (entYear != 0 && classNum != null && !classNum.equals("0") &&
            subjectCd != null && !subjectCd.equals("0")) {
            students = sDao.filterBySubject(school, entYear, classNum, subjectCd);
        }

        // 学生番号による個別検索
        if (studentNo != null && !studentNo.isEmpty()) {
            Test test = new Test();
            Student student = new Student();
            student.setNo(studentNo);
            test.setStudent(student);

            TestListStudentDAO dao = new TestListStudentDAO();
            tests = dao.filter(test);

        // 学生リストがある場合の検索（1人目のみ）
        } else if (!students.isEmpty()) {
            Student student = students.get(0);

            Test test = new Test();
            test.setStudent(student);

            TestListStudentDAO dao = new TestListStudentDAO();
            tests = dao.filter(test);
        }

        // ログ出力
        System.out.println("検索結果件数: " + tests.size());
        if (!tests.isEmpty()) {
            TestListStudent t = tests.get(0);
            System.out.println("1件目のsubjectName: " + t.getSubjectName());
        }

        // 各種リストの作成
        LocalDate todayDate = LocalDate.now();
        int year = todayDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        ClassNumDAO cNumDao = new ClassNumDAO();
        List<String> classNumList = cNumDao.filter(school);

        List<Integer> testNoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            testNoList.add(i);
        }

        SubjectDAO subjectDao = new SubjectDAO();
        List<Subject> subjectList = subjectDao.filter(school);

     // 最後の方に追加
        if (studentNo != null && !studentNo.isEmpty()) {
            request.setAttribute("mode", "student");
        } else {
            request.setAttribute("mode", "subject");
        }


        // リクエスト属性の設定
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", studentNo); // 文字列のまま
        request.setAttribute("students", students);
        request.setAttribute("errors", errors);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("test_no_list", testNoList);
        request.setAttribute("subjects", subjectList);
        request.setAttribute("tests", tests);

        return "/scoremanager/main/test_list.jsp";
    }
}

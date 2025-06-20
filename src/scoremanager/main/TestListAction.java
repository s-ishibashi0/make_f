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

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");
        String testNoStr = request.getParameter("f4");

        int entYear = 0;
        int testNo = 0;

        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            errors.put("f1", "入学年度は数値で入力してください");
        }

        try {
            testNo = Integer.parseInt(testNoStr);
        } catch (NumberFormatException e) {
            errors.put("f4", "回数は数値で入力してください");
        }

        List<Student> students = new ArrayList<>();

        // 学生一覧取得
        StudentDAO sDao = new StudentDAO();
        if (entYear != 0 && classNum != null && !classNum.equals("0") && subjectCd != null && !subjectCd.equals("0")) {
            students = sDao.filterBySubject(school, entYear, classNum, subjectCd);
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

        // Subjectリスト取得
        SubjectDAO subjectDao = new SubjectDAO();
        List<Subject> subjectList = subjectDao.filter(school);

        List<TestListStudent> testList = new ArrayList<>();

        if (!students.isEmpty()) {
            Student student = students.get(0);

            Test test = new Test();
            test.setStudent(student);
            test.setNo(testNo);

            TestListStudentDAO testDao = new TestListStudentDAO();
            testList = testDao.filter(test);

            System.out.println("成績リスト件数: " + testList.size());
        } else {
            // 学生がいなければ空のまま or 必要に応じて別の処理を追加
        }

        // リクエスト属性の設定
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", testNo);
        request.setAttribute("students", students);
        request.setAttribute("errors", errors);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("test_no_list", testNoList);
        request.setAttribute("subjects", subjectList);
        request.setAttribute("tests", testList);

        return "/scoremanager/main/test_list.jsp";
    }
}

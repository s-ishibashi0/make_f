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
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestRegistAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        School school = new School();
        school.setCd(teacher.getSchool());

        SubjectDAO subjectDao = new SubjectDAO();
        List<Subject> subjectList = subjectDao.filter(school);
        request.setAttribute("subjectList", subjectList);

        String method = request.getMethod();
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

        // POST処理
        if (method.equalsIgnoreCase("POST")) {
            Map<String, String[]> paramMap = request.getParameterMap();
            Map<String, Integer> scoreMap = new HashMap<>();

            for (String key : paramMap.keySet()) {
                if (key.startsWith("scores[")) {
                    String studentNo = key.substring(7, key.length() - 1);
                    String[] values = paramMap.get(key);
                    if (values != null && values.length > 0) {
                        try {
                            int score = Integer.parseInt(values[0]);
                            if (score >= 0 && score <= 100) {
                                scoreMap.put(studentNo, score);
                            } else {
                                errors.put("score_" + studentNo, "得点は0～100の間で入力してください");
                            }
                        } catch (NumberFormatException e) {
                            errors.put("score_" + studentNo, "得点は数値で入力してください");
                        }
                    }
                }
            }

            if (errors.isEmpty()) {
                try {
                    TestListSubjectDAO dao = new TestListSubjectDAO();
                    dao.saveScores(subjectCd, testNo, scoreMap);

                    // 成功後、完了ページにフォワード
                    return "/scoremanager/main/test_regist_done.jsp";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "/scoremanager/main/error.jsp";
                }
            }
        }

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

        return "/scoremanager/main/test_regist.jsp";
    }

}
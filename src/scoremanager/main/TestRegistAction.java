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
import tool.Action;

public class TestRegistAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        School school = new School();
        school.setCd(teacher.getSchool());

        SubjectDAO subjectDao = new SubjectDAO();
        List<Subject> subjectList = subjectDao.filter(school); // ← OK
        request.setAttribute("subjectList", subjectList); // JSPへ渡す

     // 科目コード取得

     // 旧: String subjectCd = request.getParameter("subject_cd");
        String subjectCd = request.getParameter("f3");
        request.setAttribute("f3", subjectCd);  // ← 必須、JSPに戻す





        System.out.println("teacher: " + teacher);
        System.out.println("teacher.getSchool(): " + teacher.getSchool());

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");

        int entYear = 0;
        List<Student> students = null;
        Map<String, String> errors = new HashMap<>();
        String isAttendStr = request.getParameter("f3");
        boolean isAttend = false;

        if ("t".equals(isAttendStr)) {
        	isAttend = true;
        } else if ("f".equals(isAttendStr)) {
        	isAttend = false;
        } else {
        	isAttendStr = ""; // 不正・未指定は空に
        }

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


        System.out.println("学校コード: " + school.getCd());

        // クラス番号一覧を取得
        List<String> classNumList = cNumDao.filter(school);

        List<Integer> testNoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) { // 回数は適宜変更可
            testNoList.add(i);
        }




        // 入学年度のパース
        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "入学年度は数値で入力してください");
            }
        }

        // 出席情報のパース
        if ("t".equals(isAttendStr)) {
            isAttend = true;
        }

        String noStr = request.getParameter("f4");
        int no = 0;
        if (noStr != null && !noStr.isEmpty()) {
        	try {
        		no = Integer.parseInt(noStr);
        	} catch (NumberFormatException e) {
        		errors.put("f4", "回数は数値で入力してください");
        	}
        }



        // 学生リストの取得ロジック
     // --- 学生リストの取得ロジック（subjectCdを考慮） ---
        if (subjectCd != null && !subjectCd.equals("0")) {
        	if (entYear != 0 && classNum != null && !classNum.equals("0")) {
        		// 科目・入学年度・クラス指定あり → filterBySubjectで検索
        		students = sDao.filterBySubject(school, entYear, classNum, subjectCd);
        	} else {
        		errors.put("f1", "科目で検索する場合は入学年度とクラスを指定してください");
        		students = new ArrayList<>();
        	}
        } else {
        	// subjectCdなしの場合、通常の条件分岐
        	if (entYear != 0 && classNum != null && !classNum.equals("0")) {
        		students = sDao.filter(school, entYear, classNum, isAttend);
        	} else if (entYear != 0 && (classNum == null || classNum.equals("0"))) {
        		students = sDao.filter(school, entYear, isAttend);
        	} else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
        		students = sDao.filter(school, isAttend);
        	} else {
        		errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
        		students = sDao.filter(school, isAttend);
        	}
        }



        // リクエスト属性にセット
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttendStr);
        request.setAttribute("f4", no);
        request.setAttribute("test_no_list", testNoList);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("errors", errors);

        return "/scoremanager/main/test_regist.jsp";

	}
}
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDAO;
import tool.Action;

public class StudentCreateAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // 入学年度リストの作成（例：現在年から過去10年分）
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 10; i <= currentYear; i++) {
            entYearList.add(i);
        }

        // クラス番号リストの取得
        School school = new School();
        school.setCd(teacher.getSchool());

        ClassNumDAO cDao = new ClassNumDAO();
        List<String> classNumList = cDao.filter(school);

        // JSPに渡す
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classNumList", classNumList);

        return "/scoremanager/main/student_create.jsp";
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {

    private String baseSql = "SELECT ent_year, student_no, student_name, class_num, subject_id, point " +
                             "FROM test_list_subject WHERE 1=1";

    /**
     * ResultSet を List<TestListSubject> に変換するメソッド
     */
    private List<TestListSubject> postFilter(ResultSet rSet) {
        List<TestListSubject> list = new ArrayList<>();

        try {
            while (rSet.next()) {
                String studentNo = rSet.getString("student_no");

                TestListSubject bean = list.stream()
                    .filter(b -> b.getStudentNo().equals(studentNo))
                    .findFirst()
                    .orElseGet(() -> {
                        TestListSubject newBean = new TestListSubject();
                        try {
                            newBean.setEntYear(rSet.getInt("ent_year"));
                            newBean.setStudentNo(studentNo);
                            newBean.setStudentName(rSet.getString("student_name"));
                            newBean.setClassNum(rSet.getString("class_num"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        list.add(newBean);
                        return newBean;
                    });

                bean.putPoint(rSet.getInt("subject_id"), rSet.getInt("point"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 条件でデータを取得するメソッド
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {
        List<TestListSubject> list = new ArrayList<>();
        String sql = baseSql + " AND ent_year = ? AND class_num = ?";

        if (subject != null) {
            sql += " AND subject_id = ?";
        }

        if (school != null) {
            sql += " AND school_id = ?";
        }

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);  // ← 修正済み！

            int index = 3;
            if (subject != null) {
                stmt.setString(index++, subject.getCd());
            }
            if (school != null) {
                stmt.setString(index, school.getCd());
            }

            ResultSet rs = stmt.executeQuery();
            list = postFilter(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    // 🔹 ベースとなるSQL（再利用可能）
    private final String baseSql = "SELECT subject_name, subject_cd, num, point FROM test_list_student";

    /**
     * 🔁 ResultSet から TestListStudent オブジェクトのリストを作成
     */
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent student = new TestListStudent();
            student.setSubjectName(rSet.getString("subject_name"));
            student.setSubjrctCd(rSet.getString("subject_cd"));  // ← subjectCd に直すのがベター
            student.setNum(rSet.getInt("num"));
            student.setPoint(rSet.getInt("point"));
            list.add(student);
        }
        return list;
    }

    /**
     * 🎯 フィルター条件付きの成績検索（Student情報を使用）
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list;

        // ここでは学籍番号（student.getId()）で絞り込む例
        String sql = baseSql + " WHERE student_id = ?";

        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, student.getNo());

            try (ResultSet rs = stmt.executeQuery()) {
                list = postFilter(rs);
            }
        }

        return list;
    }

    /**
     * 📄 全件取得（フィルタなし）
     */
//    public List<TestListStudent> findAll() throws Exception {
//        List<TestListStudent> list;
//
//        try (
//            Connection conn = getConnection();
//            PreparedStatement stmt = conn.prepareStatement(baseSql);
//            ResultSet rs = stmt.executeQuery();
//        ) {
//            list = postFilter(rs);
//        }
//
//        return list;
//    }
}

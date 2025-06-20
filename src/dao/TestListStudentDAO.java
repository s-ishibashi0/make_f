package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Test;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    // baseSql フィールド（共通SQL文）
	private final String baseSql =
		    "SELECT s.ent_year, s.class_num, s.name AS student_name, " +
		    "t.student_no, t.subject_cd, sub.name AS subject_name, t.no, t.point " +
		    "FROM test t " +
		    "JOIN subject sub ON t.subject_cd = sub.cd " +
		    "JOIN student s ON t.student_no = s.no";


    // ResultSet から TestListStudent のリストを作成するメソッド
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
	    List<TestListStudent> list = new ArrayList<>();
	    while (rSet.next()) {
	        TestListStudent test = new TestListStudent();
	        test.setEntYear(rSet.getInt("ent_year"));
	        test.setClassNum(rSet.getString("class_num"));
	        test.setStudentName(rSet.getString("student_name"));  // ←追加
	        test.setStudentNo(rSet.getInt("student_no"));
	        test.setSubjectCd(rSet.getString("subject_cd"));
	        test.setSubjectName(rSet.getString("subject_name"));
	        test.setNum(rSet.getInt("no")); // 回数
	        test.setPoint(rSet.getInt("point"));
	        list.add(test);
	    }
	    return list;
	}


    /**
     * student_no で絞り込み成績取得
     */
	public List<TestListStudent> filter(Test test) throws Exception {
	    List<TestListStudent> list = new ArrayList<>();
	    String studentNoStr = test.getStudent().getNo();

	    // 安全にパース
	    int studentNo;
	    try {
	        studentNo = Integer.parseInt(studentNoStr);
	    } catch (NumberFormatException e) {
	        return list; // 空リストを返す
	    }

	    String sql = baseSql + " WHERE student_no = ?";

	    try (
	        Connection conn = getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	    ) {
	        stmt.setInt(1, studentNo);
	        try (ResultSet rs = stmt.executeQuery()) {
	            list = postFilter(rs);
	        }
	    }

	    return list;
	}


    /**
     * 学校・入学年度・クラス・科目コード・回数で絞り込み成績取得
     */
    public List<TestListStudent> findByFilter(School school, int entYear, String classNum, String subjectCd, int testNo) throws Exception {
        String sql =
            "SELECT s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, " +
            "t.no, t.point, t.subject_cd, sub.name AS subject_name " +
            "FROM STUDENT s " +
            "JOIN TEST t ON s.no = t.student_no AND s.school_cd = t.school_cd " +
            "JOIN SUBJECT sub ON t.subject_cd = sub.cd AND t.school_cd = sub.school_cd " +
            "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? " +
            "AND t.subject_cd = ? AND t.no = ? " +
            "ORDER BY s.no";

        List<TestListStudent> list = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setString(4, subjectCd);
            ps.setInt(5, testNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TestListStudent test = new TestListStudent();
                    test.setEntYear(rs.getInt("ent_year"));
                    test.setClassNum(rs.getString("class_num"));
                    test.setStudentNo(rs.getInt("student_no"));
                    test.setStudentName(rs.getString("student_name"));
                    test.setNum(rs.getInt("no"));
                    test.setPoint(rs.getInt("point"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setSubjectName(rs.getString("subject_name"));
                    list.add(test);
                }
            }
        }

        return list;
    }

}

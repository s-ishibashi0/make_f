package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {

    // base.sql のSQL文
    private final String baseSql = "SELECT NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD FROM STUDENT WHERE ";

    // postFilter (学校情報を基に結果をフィルタリング)
    public List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                list.add(student);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

    // filter (学校、入学年、クラス番号、出席情報でフィルタリング)
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        String sql = baseSql + "SCHOOL_CD = ? AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";
        List<Student> students = new ArrayList<>();

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setBoolean(4, isAttend);
            ResultSet rs = ps.executeQuery();
            students = postFilter(rs, school);
        }

        return students;
    }

    // filter (学校、入学年、出席情報でフィルタリング) クラス番号なし
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        String sql = baseSql + "SCHOOL_CD = ? AND ENT_YEAR = ? AND IS_ATTEND = ?";
        List<Student> students = new ArrayList<>();

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setBoolean(3, isAttend);
            ResultSet rs = ps.executeQuery();
            students = postFilter(rs, school);
        }

        return students;
    }

    // filter (学校、出席情報でフィルタリング)
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        String sql = baseSql + "SCHOOL_CD = ? AND IS_ATTEND = ?";
        List<Student> students = new ArrayList<>();

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            ps.setBoolean(2, isAttend);

            try (ResultSet rs = ps.executeQuery()) {
                students = postFilter(rs, school);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("データ取得時にエラー発生: " + e.getMessage());
        }

        return students;
    }

    // 学生番号で1人の学生情報を取得
    public Student get(String no) throws Exception {
        String sql = "SELECT NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD FROM STUDENT WHERE NO = ?";
        Student student = null;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, no);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));

                School school = new School();
                school.setCd(rs.getString("school_cd"));
                student.setSchool(school);
            }
        }

        return student;
    }

    // update (学生情報を更新)
    public boolean update(Student student) throws Exception {
        String sql = "UPDATE STUDENT SET NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ? WHERE NO = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getEntYear());
            ps.setString(3, student.getClassNum());
            ps.setBoolean(4, student.isAttend());
            ps.setString(5, student.getNo());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // ✅ 修正済み: 科目コードでフィルタリング（TEST テーブル使用）
    public List<Student> filterBySubject(School school, int entYear, String classNum, String subjectCd) throws Exception {
        String sql = "SELECT DISTINCT S.NO, S.NAME, S.ENT_YEAR, S.CLASS_NUM, S.IS_ATTEND, S.SCHOOL_CD " +
                     "FROM STUDENT S " +
                     "JOIN TEST T ON S.NO = T.STUDENT_NO " +
                     "WHERE S.SCHOOL_CD = ? AND S.ENT_YEAR = ? AND S.CLASS_NUM = ? AND T.SUBJECT_CD = ?";
        List<Student> students = new ArrayList<>();

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setString(4, subjectCd);

            try (ResultSet rs = ps.executeQuery()) {
                students = postFilter(rs, school);
            }
        }

        return students;
    }

    // save (学生情報を保存)
    public boolean save(Student student) throws Exception {
        String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.isAttend());
            ps.setString(6, student.getSchool().getCd());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
}

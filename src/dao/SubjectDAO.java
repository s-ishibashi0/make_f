package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    // SCHOOL_CDで絞り込んだSubject一覧を取得
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT CD, NAME FROM SUBJECT WHERE SCHOOL_CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, school.getCd());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setCd(rs.getString("CD"));
                    subject.setName(rs.getString("NAME"));
                    subject.setSchool(school);
                    list.add(subject);
                }
            }
        }
        return list;
    }

    // 新規登録
    public void insert(Subject subject) throws Exception {
        String sql = "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (subject.getSchool() == null || subject.getSchool().getCd() == null) {
                throw new IllegalArgumentException("SubjectのSchoolまたはSchoolのCDが設定されていません");
            }

            stmt.setString(1, subject.getSchool().getCd());
            stmt.setString(2, subject.getCd());
            stmt.setString(3, subject.getName());

            int count = stmt.executeUpdate();
            System.out.println("insert実行件数: " + count);
        }
    }

    // 更新
    public void update(Subject subject) throws Exception {
        String sql = "UPDATE SUBJECT SET NAME = ? WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getSchool().getCd());
            stmt.setString(3, subject.getCd());
            stmt.executeUpdate();
        }
    }

    // 削除
    public void delete(Subject subject) throws Exception {
        String sql = "DELETE FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getSchool().getCd());
            stmt.setString(2, subject.getCd());
            stmt.executeUpdate();
        }
    }

    // getメソッド（CDとSchoolで特定）
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        String sql = "SELECT CD, NAME FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, school.getCd());
            stmt.setString(2, cd);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCd(rs.getString("CD"));
                    subject.setName(rs.getString("NAME"));
                    subject.setSchool(school);
                }
            }
        }
        return subject;
    }

    // saveメソッド（新規or更新）
    public boolean save(Subject subject) throws Exception {
        Subject existing = get(subject.getCd(), subject.getSchool());
        if (existing == null) {
            insert(subject);
            return true;
        } else {
            update(subject);
            return true;
        }
    }
}

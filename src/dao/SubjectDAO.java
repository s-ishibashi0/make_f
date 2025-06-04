package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {

    // 全件取得
    public List<Subject> filter() throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT CD, NAME FROM SUBJECT";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                list.add(subject);
            }
        }
        return list;
    }

    public void insert(Subject subject) throws Exception {
        String sql = "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Schoolオブジェクトがnullでないことをチェック
            if (subject.getSchool() == null || subject.getSchool().getCd() == null) {
                throw new IllegalArgumentException("SubjectのSchoolまたはSchoolのCDが設定されていません");
            }

            stmt.setString(1, subject.getSchool().getCd()); // SchoolのCDを取得
            stmt.setString(2, subject.getCd());
            stmt.setString(3, subject.getName());

            int count = stmt.executeUpdate();
            System.out.println("insert実行件数: " + count);
        }
    }


    // 変更（CDで特定してNAMEを変更）
    public void update(Subject subject) throws Exception {
        String sql = "UPDATE SUBJECT SET NAME = ? WHERE CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getCd());
            stmt.executeUpdate();
        }
    }

    // 削除（CDで特定して削除）
    public void delete(String cd) throws Exception {
        String sql = "DELETE FROM SUBJECT WHERE CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cd);
            stmt.executeUpdate();
        }
    }
}

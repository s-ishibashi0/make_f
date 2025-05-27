package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {

    // すべての科目を取得するメソッド
    public List<Subject> filter() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT CD, NAME FROM SUBJECT";  // SCHOOL_CD を削除

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                list.add(subject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list; // 取得した科目リストを返す
    }

    // 特定の科目を取得するメソッド
    public Subject get(String cd) {
        Subject subject = null;
        String sql = "SELECT CD, NAME FROM SUBJECT WHERE CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cd); // 科目コードをセット

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    // 科目情報を保存するメソッド
    public boolean save(Subject subject) {
        boolean exists = get(subject.getCd()) != null;  // 学校情報を除いた科目の存在確認
        String sql = exists
            ? "UPDATE SUBJECT SET NAME = ? WHERE CD = ?"
            : "INSERT INTO SUBJECT (CD, NAME) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (exists) {
                stmt.setString(1, subject.getName());
                stmt.setString(2, subject.getCd());
            } else {
                stmt.setString(1, subject.getCd());
                stmt.setString(2, subject.getName());
            }

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 科目情報を削除するメソッド
    public boolean delete(Subject subject) {
        String sql = "DELETE FROM SUBJECT WHERE CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getCd());  // 科目コードをセット

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
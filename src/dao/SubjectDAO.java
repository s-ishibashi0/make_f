package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {

    /**
     * すべての科目を取得するメソッド
     * @return 科目のリスト
     * @throws Exception DBアクセスエラーの場合
     */
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
        // 例外は呼び出し元に投げるのでcatch不要
        return list;
    }
}

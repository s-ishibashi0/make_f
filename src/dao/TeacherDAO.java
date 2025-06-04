package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDAO extends DAO {
    public Teacher search(String id, String password) throws Exception {
        Teacher teacher = null;

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "SELECT ID, PASSWORD, SCHOOL_CD FROM TEACHER WHERE ID=? AND PASSWORD=?")) {

            st.setString(1, id);
            st.setString(2, password);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
                    teacher.setPassword(rs.getString("password"));
                    teacher.setSchool(rs.getString("school_cd")); // ★ 学校コードをセット
                }
            }
        }

        return teacher;
    }
}

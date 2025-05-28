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
                "select * from teacher where id=? and password=?")) {

            st.setString(1, id);
            st.setString(2, password);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
//                    teacher.setLogin(rs.getString("login"));
                    teacher.setPassword(rs.getString("password"));
                }
            }
        }

        return teacher;
    }
}

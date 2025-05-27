package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDAO extends DAO{
	public Teacher search(String id, String password)
		throws Exception {
		Teacher teacher=null;

		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
				"select * from teacher where login=? and password=?");
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			teacher=new Teacher();
			teacher.setID(rs.getString("id"));
//			下の文いらないかもしれないからコメントアウトした
//			teacher.setLogin(rs.getString("login"));
			teacher.setPassword(rs.getString("password"));
		}

		rs.close();
		st.close();
		con.close();
		return teacher;
	}
}
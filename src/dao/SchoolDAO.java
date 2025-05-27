package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDAO extends DAO {

	public School get(String cd) throws Exception {
		School school = null;
		String sql = "SELECT cd, name FROM school WHERE cd = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, cd);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				school = new School();
				school.setCd(rs.getString("cd"));
				school.setName(rs.getString("name"));
			}
		}

		return school;
	}
}
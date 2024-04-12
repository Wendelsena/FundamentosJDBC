package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program2 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO actor"
					+ "(first_name, last_name)"
					+ "VALUES"
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS); // retorna o ID do obj inserido
			st.setString(1, "Wendel");
			st.setString(2, "Sena");
			
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1); // retorna o valor da primeira colula
					System.out.println("Done! Add object: " + id);
				}
			} else {
				System.out.println("No rows Affected!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}

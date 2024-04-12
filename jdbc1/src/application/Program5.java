package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program5 {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); // não confirma a operação de primeira.
			
			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE actor SET last_name = 'FREITAS' WHERE actor_id <= 10");

			//int x = 1;
			//if (x < 2) {
			//	throw new SQLException("Fake error!!!");
			//}

			int rows2 = st.executeUpdate("UPDATE actor SET last_name = 'SENA' WHERE actor_id > 10 AND actor_id <= 20");

			conn.commit(); // logica de transação

			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback(); // logica para retornar à logica
				throw new DbException("Trasaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}

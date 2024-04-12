package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null; // conecta com o B.D.
		Statement st = null; // prepara a consulta.
		ResultSet rs = null; // guarda os resultados da consulta.

		try {
			conn = DB.getConnection(); // conexão.
			st = conn.createStatement(); // isntancia um obj do tipo Statement.

			// executeQuery pega o string de pesquisa.
			rs = st.executeQuery("select * from film"); // recebe o resultado da pesquisa

			while (rs.next()) {
				System.out.println(rs.getInt("film_id") + ", " + rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}

package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = DB.getConnection(); // conecta com o B.D (codigo no BD.java).
		DB.closeConnection(); // fecha a conexão com o B.D (codigo no BD.java).

	}

}

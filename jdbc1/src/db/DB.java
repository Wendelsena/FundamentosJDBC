package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	// gera a conexão com o B.D
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadPropeties(); // pega as própriedades do B.D
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props); // conecta as propriedades pegadas ao B.D
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	// fecha a conexão com o B.D
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	// le as propriedades do banco de dados no file db.proprieties
	private static Properties loadPropeties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs); // faz a leitura do arq db.proprieties
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}

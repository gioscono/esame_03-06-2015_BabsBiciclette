package it.polito.tdp.babs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	static private final String jdbcUrl = "jdbc:mysql://localhost/babs?user=root";
	static private DBConnect instance = null;

	private DBConnect() {
		instance = this;
	}

	public static DBConnect getInstance() {
		if (instance == null)
			return new DBConnect();
		else {
			return instance;
		}
	}

	public Connection getConnection() {
		try {
			
			Connection conn = DriverManager.getConnection(jdbcUrl);
			return conn;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al database");
		}
	}
}

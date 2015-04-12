package net.alpha.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/alpha_dev?useUnicode=true&characterEncoding=utf8";
		String id = "alpha";
		String pw = "1q2w3e4r";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}
}

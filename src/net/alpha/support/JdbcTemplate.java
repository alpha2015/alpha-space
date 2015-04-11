package net.alpha.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JdbcTemplate {
	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);
	
	public Connection getConnection() {
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

	public void executeUpdate(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
	} 
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}

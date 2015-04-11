package net.alpha.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.alpha.support.JdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO {
	private final static Logger logger = LoggerFactory.getLogger(UserDAO.class);

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

	public void addUser(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(){
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return null;
			}
			
		}; 
		String sql = "insert into USERS values(?,?,?,?)";
		jdbcTemplate.executeUpdate(sql);
	}

	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}

				return new User(rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
		
		String sql = "select * from USERS where userId = ?";;
		return (User)jdbcTemplate.executeQuery(sql);
	}

	public void removeUser(String userId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		};
		String sql = "delete from USERS where userId = ?";
		jdbcTemplate.executeUpdate(sql);
	}

	public void updateUser(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		}; 
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		jdbcTemplate.executeUpdate(sql);
	}

}

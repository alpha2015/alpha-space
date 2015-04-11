package net.alpha.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.alpha.support.JdbcTemplate;
import net.alpha.support.PreparedStatementSetter;
import net.alpha.support.RowMapper;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		jdbcTemplate.executeUpdate(sql, pss);
	}

	public User findByUserId(String userId) throws SQLException {
		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from USERS where userId = ?";;
		return (User)jdbcTemplate.executeQuery(sql,pss, rm);
	}

	public void removeUser(String userId) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "delete from USERS where userId = ?";
		jdbcTemplate.executeUpdate(sql, pss);
	}

	public void updateUser(User user) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};
 		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		jdbcTemplate.executeUpdate(sql, pss);
	}

}

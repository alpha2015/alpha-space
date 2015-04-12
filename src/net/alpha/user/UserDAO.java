package net.alpha.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.alpha.support.JdbcTemplate;
import net.alpha.support.RowMapper;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) throws SQLException {
		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from USERS where userId = ?";
		;
		return jdbcTemplate.executeQuery(sql, rm, userId);
	}

	public void removeUser(String userId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "delete from USERS where userId = ?";
		jdbcTemplate.executeUpdate(sql, userId);
	}

	public void updateUser(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		jdbcTemplate.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

}

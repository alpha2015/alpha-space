package net.alpha.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.alpha.jdbc.JdbcTemplate;
import net.alpha.jdbc.RowMapper;

public class UserDAO {
	public void addUser(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) {
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

	public List<User> findAllUser() {
		RowMapper<List<User>> rm = new RowMapper<List<User>>() {
			@Override
			public List<User> mapRow(ResultSet rs) throws SQLException {
				List<User> list = new ArrayList<User>();
				while (rs.next()) {
					list.add(new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs
							.getString("email")));
				}
				return list;
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from USERS";
		;
		return jdbcTemplate.allList(sql, rm);
	}

	public void removeUser(String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "delete from USERS where userId = ?";
		jdbcTemplate.executeUpdate(sql, userId);
	}

	public void updateUser(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		jdbcTemplate.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

}

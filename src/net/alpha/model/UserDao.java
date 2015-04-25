package net.alpha.model;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDao extends JdbcDaoSupport {
	public void addUser(User user) {
		String sql = "insert into USERS values(?,?,?,?)";
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) {
		String sql = "select * from USERS where userId = ?";
		return getJdbcTemplate().queryForObject(
				sql,
				(rs, rowNum) -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs
						.getString("email")), userId);
	}

	public List<User> findAllUser() {
		String sql = "select * from USERS";
		return getJdbcTemplate().query(
				sql,
				(rs, rowNum) -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs
						.getString("email")));
	}

	public void removeUser(String userId) {
		String sql = "delete from USERS where userId = ?";
		getJdbcTemplate().update(sql, userId);
	}

	public void updateUser(User user) {
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		getJdbcTemplate().update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

}

package net.alpha.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.alpha.model.PasswordMismatchException;
import net.alpha.model.User;
import net.alpha.model.UserDao;
import net.alpha.model.UserNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	public static User TEST_USER = new User("userId", "password", "name", "javajigi@slipp.net");
	private UserDao userDao;
	
	@Before
	public void setup() throws Exception {
		userDao = new UserDao();
		userDao.removeUser(TEST_USER.getUserId());
	}

	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}

	@Test
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}
	
	@Test
	public void login() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.addUser(user);
		User.login(TEST_USER.getUserId(), "password2");
	}
}

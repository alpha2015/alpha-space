package net.alpha.model;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.alpha.model.User;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidatorTest {
	private final static Logger logger = LoggerFactory.getLogger(UserValidatorTest.class);
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void userIdIsNull() {
		User user = new User(null, "password", "name", "");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals(1, constraintViolations.size());
		logger.debug(constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void userIdLength() throws Exception {
		User user = new User("us", "password", "name", "");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals(1, constraintViolations.size());
		logger.debug(constraintViolations.iterator().next().getMessage());

		user = new User("userIduserId2", "password", "name", "");
		constraintViolations = validator.validate(user);
		assertEquals(1, constraintViolations.size());
		logger.debug(constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void email() throws Exception {
		User user = new User("user", "password", "name", "email");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals(1, constraintViolations.size());
		logger.debug(constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void invalidUser() throws Exception {
		User user = new User("us", "password", "name", "email");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals(2, constraintViolations.size());
		Iterator<ConstraintViolation<User>> violations = constraintViolations.iterator();
		while (violations.hasNext()) {
			ConstraintViolation<User> each = violations.next();
//			logger.debug(constraintViolations.iterator().next().getMessage());
			logger.debug(each.getMessage());
		}
	}
}

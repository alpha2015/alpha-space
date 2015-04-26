package net.alpha.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import net.alpha.model.User;
import net.alpha.model.UserDao;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.support.MyValidatorFactory;
import core.support.SessionUtils;

@Controller
public class UpdateUserServlet {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		HttpSession session = request.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if (sessionUserId == null) {
			return "redirect:/";
		}

		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(e1);
		}

		if (!user.isSameUser(sessionUserId)) {
			return "redirect:/";
		}

		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (constraintViolations.size() > 0) {
			request.setAttribute("isUpdate", true);
			request.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			request.setAttribute("errorMessage", errorMessage);
			return "form";
		}
		userDao.updateUser(user);
		return "redirect:/";
	}
}

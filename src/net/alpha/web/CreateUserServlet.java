package net.alpha.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@Controller
public class CreateUserServlet {
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(e1);
		}

		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (constraintViolations.size() > 0) {
			request.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			request.setAttribute("errorMessage", errorMessage);
			return "form";
		}
		User test = userDao.findByUserId(user.getUserId());
		if(test.getUserId() != null){
			request.setAttribute("errorMessage", "이미 존재하는 아이디입니다.");
			return "form";
		}
		userDao.addUser(user);
		return "redirect:/";
	}
}

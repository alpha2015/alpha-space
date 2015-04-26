package net.alpha.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.alpha.model.PasswordMismatchException;
import net.alpha.model.User;
import net.alpha.model.UserDao;
import net.alpha.model.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginServlet {
	public static final String SESSION_USER_ID = "userId";
	
	@Autowired
	private UserDao userDao;

	@RequestMapping("/users/login")
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		return "login";
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		try {
			User.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_USER_ID, userId);
			return "redirect:/";
		} catch (UserNotFoundException e) {
			request.setAttribute("errorMessage", "존재하지 않는 사용자 입니다. 다시 로그인하세요.");
			return "login";
		} catch (PasswordMismatchException e) {
			request.setAttribute("errorMessage", "비밀번호가 틀립니다. 다시 로그인하세요.");
			return "login";
		}
	}
}

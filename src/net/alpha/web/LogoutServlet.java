package net.alpha.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutServlet {
	@RequestMapping("/users/logout")
	protected String doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("userId");
		return "redirect:/";
	}
}

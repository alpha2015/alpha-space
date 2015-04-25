package net.alpha.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.alpha.model.User;
import net.alpha.model.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import core.support.SessionUtils;

@Controller
public class UpdateFormUserServlet {
	private final static Logger logger = LoggerFactory.getLogger(UpdateFormUserServlet.class);

	@Autowired
	UserDao userDao;
	
	@RequestMapping("/users/updateForm")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if (userId == null) {
			resp.sendRedirect("/");
			return;
		}
		logger.debug("User Id : {}", userId);
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		req.setAttribute("isUpdate", true);
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
		rd.forward(req, resp);
	}
}

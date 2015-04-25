package net.alpha.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.alpha.model.User;
import net.alpha.model.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ApiFindUserServlet {
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/api/users/find")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		if (userId == null) {
			resp.sendRedirect("/");
		}

		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			return;
		}

		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		final Gson gson = builder.create();

		String jsonData = gson.toJson(user);
		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		out.print(jsonData);
	}
}

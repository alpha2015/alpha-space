package net.alpha.web;

import net.alpha.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateFormUserServlet {
	@RequestMapping("/users/createForm")
	protected String doGet(Model model){
		model.addAttribute("user", new User());
		return "form";
	}
}

package com.project.Online_Quiz_App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Online_Quiz_App.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("username")
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoUserLoginPage() {
//		model.put("user", new User());
		return "Userlogin";
	}

	@RequestMapping(value = "/user-login", method = RequestMethod.POST)
	public String userLogin(@RequestParam String username, @RequestParam String password, ModelMap model) {
		if (userService.login(username, password)) {
			model.put("username", username);
			return "redirect:/user-dashboard";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
	public String gotoSignupPage() {
//		model.put("user", new User());
		return "UserSignUp";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public String signup(@RequestParam String username, @RequestParam String password) {

		userService.signUp(username, password);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate session
		}
		return "redirect:/?logout"; // Redirect to login page with logout parameter
	}
}

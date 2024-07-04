package com.project.Online_Quiz_App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Online_Quiz_App.entities.Admin;
import com.project.Online_Quiz_App.entities.Quiz;
import com.project.Online_Quiz_App.services.AdminService;
import com.project.Online_Quiz_App.services.AppDbService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("adminId")
public class AdminController {
	@Autowired
	AdminService adminService;

	@Autowired
	AppDbService appDbService;

	@RequestMapping(value = "/admin-login", method = RequestMethod.GET)
	public String gotoAdminLogin(ModelMap model) {
		model.put("admin", new Admin("", "", ""));
		return "AdminLogin";
	}

	@RequestMapping(value = "/admin-login", method = RequestMethod.POST)
	public String AdminLogin(ModelMap model, @Valid Admin admin) {
		if (adminService.login(admin.getId(), admin.getHashedPassword())) {
			model.put("username", admin.getId());
			return "redirect:/admin-dashboard";
		}
		return "redirect:/admin-login";
	}

	@RequestMapping(value = "/admin-dashboard", method = RequestMethod.GET)
	public String goToDashBaord(ModelMap model) {

		model.put("quizzes", adminService.getListOfQuizzes());
		return "AdminDashboard";
	}

	@RequestMapping(value = "/add-quiz", method = RequestMethod.GET)
	public String addQuiz(ModelMap model) {
//		for(int i = 0; i < 10; i++) {
//			quiz.getQuestions().add(new Question());
//		}
		model.put("quiz", new Quiz());
//		model.put("question", new Question());
		return "AddQuiz";
	}

	@RequestMapping(value = "/add-quiz", method = RequestMethod.POST)
	public String addQuiz(ModelMap model, @Valid Quiz quiz) {
//		for(Question question : quiz.getQuestions()) System.out.println(question);
//		System.out.println(quiz.toString());
		adminService.saveQuiz(quiz);
		return "redirect:/admin-dashboard";
	}

	@RequestMapping(value = "/update-quiz", method = RequestMethod.GET)
	public String updateQuiz(ModelMap model, @RequestParam int quizId) {
		model.put("quiz", adminService.getQuizById(quizId));
		return "AddQuiz";
	}

	@RequestMapping(value = "/update-quiz", method = RequestMethod.POST)
	public String updateQuiz(ModelMap model, @Valid Quiz quiz, BindingResult result) {
		if (result.hasErrors()) {
			return "AddQuiz";
		}
		appDbService.updateQuiz(quiz);
		return "redirect:admin-dashboard";
	}

	@RequestMapping(value = "/delete-quiz")
	public String deleteQuiz(@RequestParam int quizId) {
		appDbService.deleteQuiz(quizId);
		return "redirect:/admin-dashboard";
	}
	
	@RequestMapping(value = "/admin-logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate session
		}
		return "redirect:/?logout"; // Redirect to login page with logout parameter
	}
}

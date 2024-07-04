package com.project.Online_Quiz_App.controllers;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Online_Quiz_App.entities.QuizAttempts;
import com.project.Online_Quiz_App.services.UserService;

@Controller
@SessionAttributes("username")
public class UserController {
	@Autowired
	UserService userService;
	

	@RequestMapping(value = "/user-dashboard", method = RequestMethod.GET)
	public String goToDashBaord(ModelMap model) {

		model.put("quizzes", userService.getListOfAvailbleQuizzes(model.getAttribute("username").toString()));
		return "UserDashboard";
	}
	
	@RequestMapping(value = "/start-quiz", method = RequestMethod.GET)
	public String goToQuizPage(@RequestParam int quizId, ModelMap model) {
		model.put("questions", userService.getQuizQuestions(quizId));
		model.put("quizId", quizId);
		return "Quiz";
	}
	
	@RequestMapping(value = "/submit-answers", method = RequestMethod.POST)
	public String submitAnswers(@RequestParam int quizId, @RequestParam int score, ModelMap model) {
		
		userService.submitAttempt(new QuizAttempts(quizId, model.getAttribute("username").toString(), Date.valueOf(LocalDate.now()), score));
        
		return "redirect:/user-dashboard";
	}
	
	@RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
	public String goToLeaderboard(ModelMap model) {
		
		model.put("quizzes", userService.getListOfQuizzes());
		model.put("lifetimeLeadScores", userService.getLisfetimeScores());
		return "Leaderboard";
	}
	
	@RequestMapping(value = "/quiz-leaderboard", method = RequestMethod.GET)
	public String gotoQuizLeaderboard(@RequestParam int quizId, @RequestParam String quizTitle, ModelMap model) {
		model.put("quizLeadScores", userService.getQuizLeadScores(quizId));
		model.put("quizTitle", quizTitle);
		return "QuizLeaders";
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String goToHistory (ModelMap model) {
		model.put("pastAttempts", userService.getListOfPastAttempts(model.getAttribute("username").toString()));
		return "UserHistory";
	}
}

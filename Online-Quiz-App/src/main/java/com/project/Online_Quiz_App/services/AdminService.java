package com.project.Online_Quiz_App.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Online_Quiz_App.entities.Admin;
import com.project.Online_Quiz_App.entities.Quiz;
import com.project.Online_Quiz_App.repositories.AdminRepository;
import com.project.Online_Quiz_App.repositories.QuestionRepository;
import com.project.Online_Quiz_App.repositories.QuizRepository;
import com.project.Online_Quiz_App.utils.HashingUtil;

@Service
public class AdminService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	public boolean login(String username, String password) {
        Admin admin;
		try {
			admin = adminRepository.getUserByUsername(username);
			if (admin != null) {
	            String hashedPassword = HashingUtil.hashPassword(password, admin.getSalt());
	            return hashedPassword.equals(admin.getHashedPassword());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }

	public void saveQuiz(Quiz quiz) {
		try {
			int generatedQuizId = quizRepository.insert(quiz.getQuizTitle());
			questionRepository.insert(generatedQuizId, quiz.getQuestions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Quiz> getListOfQuizzes() {
		try {
			return quizRepository.getAllQuizzes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Quiz>();
	}

	public Quiz getQuizById(int quizId) {
		try {
			Quiz quiz = quizRepository.getQuiz(quizId);
			if (quiz != null) {
				quiz.setQuestions(questionRepository.getQuestions(quizId));
				return quiz;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updateQuiz(Quiz quiz) {
		try {
			if(questionRepository.deleteQuestionsOfQuizById(quiz.getQuizId()) > 0) {
				if(quizRepository.deleteQuiz(quiz.getQuizId()) > 0) {
					saveQuiz(quiz);
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}

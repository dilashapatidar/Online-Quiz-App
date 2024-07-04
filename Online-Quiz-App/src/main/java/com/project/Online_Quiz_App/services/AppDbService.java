package com.project.Online_Quiz_App.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Online_Quiz_App.entities.Quiz;
import com.project.Online_Quiz_App.entities.QuizAttempts;
import com.project.Online_Quiz_App.repositories.QuestionRepository;
import com.project.Online_Quiz_App.repositories.QuizAttemptsRepository;
import com.project.Online_Quiz_App.repositories.QuizRepository;

@Service
public class AppDbService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	QuizAttemptsRepository attemptsRepository;

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
			if (questionRepository.deleteQuestionsOfQuizById(quiz.getQuizId()) > 0) {
				if (quizRepository.deleteQuiz(quiz.getQuizId()) > 0) {
					attemptsRepository.deleteAttemptRecordsByQuizId(quiz.getQuizId());
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
	
	public int deleteQuiz(int quizId) {
		try {
			if (questionRepository.deleteQuestionsOfQuizById(quizId) > 0) {
				if (quizRepository.deleteQuiz(quizId) > 0) {
					System.out.println(2);
					attemptsRepository.deleteAttemptRecordsByQuizId(quizId);
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int recordAttempt(QuizAttempts attempts) {
		try {
			return attemptsRepository.insert(attempts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Map<String, Integer> getOverallLeaders() {
		try {
			return attemptsRepository.getOverallLeaders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Integer> getQuizLeaders(int quizId) {
		try {
			return attemptsRepository.getQuizLeaders(quizId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Map<String, QuizAttempts> getPastAttemptsByUsername(String username) {
		try {
			return attemptsRepository.getPastAttempts(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

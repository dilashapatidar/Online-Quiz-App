package com.project.Online_Quiz_App.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Online_Quiz_App.entities.Question;
import com.project.Online_Quiz_App.entities.Quiz;
import com.project.Online_Quiz_App.entities.QuizAttempts;
import com.project.Online_Quiz_App.entities.User;
import com.project.Online_Quiz_App.repositories.QuizRepository;
import com.project.Online_Quiz_App.repositories.UserRepository;
import com.project.Online_Quiz_App.utils.HashingUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppDbService appDbService;
	
	@Autowired
	private QuizRepository quizRepository;
	
	public int signUp(String username, String password) {
		String salt = HashingUtil.generateSalt();
		int signUpStatus = 0;
		try {
			String hashedPassword = HashingUtil.hashPassword(password, salt);
			
			User user = new User(username, hashedPassword, salt);
			
			//persist the user' details
			return userRepository.save(user);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signUpStatus;
	}
	
	public boolean login(String username, String password) {
        User user;
		try {
			user = userRepository.getUserByUsername(username);
			if (user != null) {
	            String hashedPassword = HashingUtil.hashPassword(password, user.getSalt());
	            return hashedPassword.equals(user.getHashedPassword());
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

	public List<Quiz> getListOfQuizzes() {
		return appDbService.getListOfQuizzes();
	}
	
	public List<Quiz> getListOfAvailbleQuizzes(String username) {
		try {
			return quizRepository.getAvailableQuizzes(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	public List<Question> getQuizQuestions(int quizId) {
		return appDbService.getQuizById(quizId).getQuestions();
	}
	
	public int submitAttempt(QuizAttempts attempts) {
		return appDbService.recordAttempt(attempts);
	}
	
	public Map<String, Integer> getLisfetimeScores() {
		return appDbService.getOverallLeaders();
	}
	
	public Map<String, Integer> getQuizLeadScores(int quizId) {
		return appDbService.getQuizLeaders(quizId);
	}
	
	public Map<String, QuizAttempts>getListOfPastAttempts (String username) {
		Map<String, QuizAttempts> records = appDbService.getPastAttemptsByUsername(username);
		return records;
	}
}

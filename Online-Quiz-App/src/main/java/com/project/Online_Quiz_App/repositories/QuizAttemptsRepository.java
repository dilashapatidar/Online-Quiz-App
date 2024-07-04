package com.project.Online_Quiz_App.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.project.Online_Quiz_App.entities.QuizAttempts;

@Repository
public class QuizAttemptsRepository {
	@Autowired
	private DataSource dataSource;
	
	@Transactional
	public int insert(QuizAttempts attempt) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO quiz_attempts(quiz_id, username, attempt_date_time, score) VALUES(?,?,?,?)")) {
			preparedStatement.setInt(1, attempt.getQuizId());
			preparedStatement.setString(2, attempt.getUsername());
			preparedStatement.setDate(3, attempt.getDateOfAttempt());
			preparedStatement.setInt(4, attempt.getScore());

			return preparedStatement.executeUpdate();
		}
	}

	public Map<String, Integer> getOverallLeaders() throws SQLException {
		Map<String, Integer> overallLeaders = new HashMap<String, Integer>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT username, SUM(score) AS total_score\r\n"
								+ "FROM (\r\n"
								+ "    SELECT username, score\r\n"
								+ "    FROM quiz_attempts\r\n"
								+ "    UNION ALL\r\n"
								+ "    SELECT username, score\r\n"
								+ "    FROM quiz_attempts_archive\r\n"
								+ ") AS combined_scores\r\n"
								+ "GROUP BY username");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				overallLeaders.put(resultSet.getString("username"), resultSet.getInt("total_score"));
			}
			return overallLeaders;
		}
	}

	public Map<String, Integer> getQuizLeaders(int quizId) throws SQLException {
		Map<String, Integer> overallLeaders = new HashMap<String, Integer>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT username, SUM(score) AS total_score\r\n" + "FROM quiz_attempts\r\n"
								+ "WHERE quiz_id = ?\r\n" + "GROUP BY username\r\n" + "ORDER BY total_score DESC")) {
			preparedStatement.setInt(1, quizId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					overallLeaders.put(resultSet.getString("username"), resultSet.getInt("total_score"));
				}
			}
			return overallLeaders;
		}
	}

	public List<QuizAttempts> getAttemptByQuizId(int quizId) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM quiz_attempts WHERE quiz_id = ?")) {
			preparedStatement.setInt(1, quizId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				List<QuizAttempts> attempts = new ArrayList<QuizAttempts>();
				while (resultSet.next()) {
					attempts.add(new QuizAttempts(resultSet.getInt("quiz_id"), resultSet.getString("username"),
							resultSet.getDate("attempt_date_time"), resultSet.getInt("score")));
				}
				return attempts;
			}
		}
	}
	@Transactional
	public int deleteAttemptRecordsByQuizId(int quizId) throws SQLException {
		archiveAttempts(quizId);

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM quiz_attempts WHERE quiz_id = ?")) {
			preparedStatement.setInt(1, quizId);
			return preparedStatement.executeUpdate();
		}
	}

	@Transactional
	public int[] archiveAttempts(int quizId) throws SQLException {
		List<QuizAttempts> attempts = getAttemptByQuizId(quizId);
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO quiz_attempts_archive(quiz_id, username, attempt_date_time, score) VALUES(?,?,?,?)")) {
			for (QuizAttempts attempt : attempts) {
				preparedStatement.setInt(1, attempt.getQuizId());
				preparedStatement.setString(2, attempt.getUsername());
				preparedStatement.setDate(3, attempt.getDateOfAttempt());
				preparedStatement.setInt(4, attempt.getScore());

				preparedStatement.addBatch();
			}
			return preparedStatement.executeBatch();
		}
	}
	
	public Map<String, QuizAttempts> getPastAttempts(String username) throws SQLException {
	    System.out.println(username);
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "SELECT quiz_title, username, attempt_date_time, score " +
	                 "FROM quiz INNER JOIN quiz_attempts " +
	                 "ON quiz.quiz_id = quiz_attempts.quiz_id " +
	                 "WHERE username = ? " +
	                 "UNION " +
	                 "SELECT quiz_title, username, attempt_date_time, score " +
	                 "FROM quiz_archive INNER JOIN quiz_attempts_archive " +
	                 "ON quiz_archive.quiz_id = quiz_attempts_archive.quiz_id " +
	                 "WHERE username = ?")) {
	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, username);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            Map<String, QuizAttempts> pastAttempts = new HashMap<String, QuizAttempts>();
	            while (resultSet.next()) {
	                pastAttempts.put(resultSet.getString("quiz_title"), new QuizAttempts(	                    
	                    resultSet.getString("username"),
	                    resultSet.getDate("attempt_date_time"),
	                    resultSet.getInt("score")
	                ));
	            }
	            return pastAttempts;
	        }
	    }
	}
}

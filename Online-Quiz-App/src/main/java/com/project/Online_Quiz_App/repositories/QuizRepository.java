package com.project.Online_Quiz_App.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.Online_Quiz_App.entities.Quiz;

@Repository
public class QuizRepository {

	@Autowired
	private DataSource dataSource;

	@Transactional
	public int insert(String quizTitle) throws SQLException {
		String sql = "INSERT INTO quiz(quiz_title) VALUES(?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			// Set parameters
			preparedStatement.setString(1, quizTitle);

			// Execute the insert statement
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating quiz failed, no rows affected.");
			}

			// Retrieve the generated key
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1); // Assuming quiz_id is of type INT
					return generatedId;
				} else {
					throw new SQLException("Creating quiz failed, no ID obtained.");
				}
			}
		}
	}

	public Quiz getQuiz(int quizId) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM quiz WHERE quiz_id = ?")) {
			preparedStatement.setInt(1, quizId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				Quiz quiz = new Quiz();
				if (resultSet.next()) {
					quiz.setQuizId(resultSet.getInt("quiz_id"));
					quiz.setQuizTitle(resultSet.getString("quiz_title"));
					return quiz;
				}
			}
		}
		return null;
	}

	public List<Quiz> getAllQuizzes() throws SQLException {
		List<Quiz> quizzes = new ArrayList<Quiz>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM quiz");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Quiz quiz = new Quiz();
				quiz.setQuizId(resultSet.getInt("quiz_id"));
				quiz.setQuizTitle(resultSet.getString("quiz_title"));
				quizzes.add(quiz);
			}
		}
		return quizzes;
	}

	@Transactional
	public int deleteQuiz(int quizId) throws SQLException {
		int deleteStatus = 0;
		archiveQuiz(quizId);
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM quiz WHERE quiz_id = ?")) {
			preparedStatement.setInt(1, quizId);
			deleteStatus = preparedStatement.executeUpdate();
			return deleteStatus;
		}
	}

	@Transactional
	public int archiveQuiz(int quizId) throws SQLException {

		Quiz quiz = getQuiz(quizId);

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO quiz_archive(quiz_id, quiz_title) VALUES(?,?)")) {
			// Set parameters
			preparedStatement.setInt(1, quizId);
			preparedStatement.setString(2, quiz.getQuizTitle());

			// Execute the insert statement
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Archiving quiz failed, no rows affected.");
			}
		}
		return 0;
	}
	
	public List<Quiz> getAvailableQuizzes(String username) throws SQLException {
		List<Quiz> quizzes = new ArrayList<Quiz>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT *\r\n" + "FROM quiz\r\n"
						+ "WHERE quiz_id NOT IN (SELECT quiz_id FROM quiz_attempts WHERE username = ?);")) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					quizzes.add(new Quiz(resultSet.getInt("quiz_id"), resultSet.getString("quiz_title")));
				}
				return quizzes;
			}
		}
	}
}


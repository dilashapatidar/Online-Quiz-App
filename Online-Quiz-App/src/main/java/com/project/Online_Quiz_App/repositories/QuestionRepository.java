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

import com.project.Online_Quiz_App.entities.Question;

@Repository
public class QuestionRepository {

	@Autowired
	private DataSource dataSource;
	
	@Transactional
	public int[] insert(int quizId, List<Question> questions) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO question" + "(quiz_id, question, option_1, option_2, option_3, "
								+ "option_4, correct_answer) " + "VALUES(?,?,?,?,?,?,?)")) {

			for (Question question : questions) {
				preparedStatement.setInt(1, quizId);
				preparedStatement.setString(2, question.getQuestion());
				preparedStatement.setString(3, question.getOption1());
				preparedStatement.setString(4, question.getOption2());
				preparedStatement.setString(5, question.getOption3());
				preparedStatement.setString(6, question.getOption4());
				preparedStatement.setInt(7, question.getCorrectAnswer());
				preparedStatement.addBatch();
			}
			int[] result = preparedStatement.executeBatch();
			return result;
		}
	}

	public List<Question> getQuestions(int quizId) throws SQLException {
		List<Question> questions = new ArrayList<Question>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM question WHERE quiz_id = ?");) {
			preparedStatement.setInt(1, quizId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while (resultSet.next()) {
					Question question = new Question();
					question.setQuestionId(resultSet.getInt("question_id"));
					question.setQuiz_id(resultSet.getInt("quiz_id"));
					question.setQuestion(resultSet.getString("question"));
					question.setOption1(resultSet.getString("option_1"));
					question.setOption2(resultSet.getString("option_2"));
					question.setOption3(resultSet.getString("option_3"));
					question.setOption4(resultSet.getString("option_4"));
					question.setCorrectAnswer(resultSet.getInt("correct_answer"));
					questions.add(question);
				}
			}
		}
		return questions;
	}
	@Transactional
	public int deleteQuestionsOfQuizById(int quizId) throws SQLException {
		int deleteStatus = -1;

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM question WHERE quiz_id = ?")) {
			preparedStatement.setInt(1, quizId);
			deleteStatus = preparedStatement.executeUpdate();
			return deleteStatus;
		}
	}
}
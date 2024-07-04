package com.project.Online_Quiz_App.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Quiz {
	private int quizId;
	private String quizTitle;
	private List<Question> questions = new ArrayList<Question>();

	public Quiz() {
		super();
		for (int i = 0; i < 10; i++) {
			questions.add(new Question());
		}
	}
	public Quiz(int quizId, String quizTitle) {
		super();
		this.setQuizId(quizId);
		this.quizTitle = quizTitle;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", quizTitle=" + quizTitle + ", questions=" + questions + "]\n";
	}

}

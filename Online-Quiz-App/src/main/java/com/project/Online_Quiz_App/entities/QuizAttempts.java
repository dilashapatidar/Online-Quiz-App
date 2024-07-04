package com.project.Online_Quiz_App.entities;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class QuizAttempts {
	private int quizId;
	private String username;
	private Date dateOfAttempt;
	private int score;

	public QuizAttempts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizAttempts(int quizId, String username, Date dateOfAttempt, int score) {
		super();
		this.quizId = quizId;
		this.username = username;
		this.dateOfAttempt = dateOfAttempt;
		this.score = score;
	}

	public QuizAttempts(String username, Date dateOfAttempt, int score) {
		super();
		this.username = username;
		this.dateOfAttempt = dateOfAttempt;
		this.score = score;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateOfAttempt() {
		return dateOfAttempt;
	}

	public void setDateOfAttempt(Date dateOfAttempt) {
		this.dateOfAttempt = dateOfAttempt;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "QuizAttempts [quizId=" + quizId + ", username=" + username + ", dateOfAttempt=" + dateOfAttempt
				+ ", score=" + score + "]";
	}
}

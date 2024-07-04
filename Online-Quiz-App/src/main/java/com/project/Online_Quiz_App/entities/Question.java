package com.project.Online_Quiz_App.entities;

import org.springframework.stereotype.Component;

@Component
public class Question {
	private int questionId;
	private int quiz_id;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int correctAnswer;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int questionId, int quiz_id, String question, String option1, String option2, String option3, String option4,
			int correctAnswer) {
		super();
		this.questionId = questionId;
		this.quiz_id = quiz_id;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", quiz_id=" + quiz_id + ", question=" + question + ", option1="
				+ option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4
				+ ", correctAnswer=" + correctAnswer + "]";
	}
	
}

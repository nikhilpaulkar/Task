package com.dto;

public class QuestionDto 
{
  private String description;
  private String question;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public QuestionDto(String description, String question) {
		super();
		this.description = description;
		this.question = question;
	}

	public QuestionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
  
  
}

package com.dto;

import java.util.Date;

public class QuestionDto 
{
  private String description;
  private String question;
  private Long userid;
  private Date date;
  private boolean draft;
  
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestion() {
		return question;
	}
    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	

    
	
	public QuestionDto(String description, String question, Long userid, Date date, boolean draft) {
		super();
		this.description = description;
		this.question = question;
		this.userid = userid;
		this.date = date;
		this.draft = draft;
	}

	public QuestionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
  
  
}

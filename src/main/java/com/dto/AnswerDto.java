package com.dto;



public class AnswerDto
{
	private String comment;
	private Long questionid;
	private long userid;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    
	
    


	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public AnswerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerDto(String comment, Long questionid, long userid) {
		super();
		this.comment = comment;
		this.questionid = questionid;
		this.userid = userid;
	}

	
	

	
	
	
}

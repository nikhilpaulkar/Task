package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE answerentity SET isactive=false WHERE id=?")
@Table(name="answerentity")
public class AnswerEntity
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String comment;
	
	private boolean isactive=true;
	
	
	@OneToOne 
	private Users user_id;

	@OneToOne
	private QuestionEntity question_id;
	
	public long getId() {
		return id;
	}
    
	public boolean isIsactive() {
		return isactive;
	}

	
	public QuestionEntity getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(QuestionEntity question_id) {
		this.question_id = question_id;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}
    



	public AnswerEntity(long id, String comment, boolean isactive, Users user_id, QuestionEntity question_id) {
		super();
		this.id = id;
		this.comment = comment;
		this.isactive = isactive;
		this.user_id = user_id;
		this.question_id = question_id;
	}

	public AnswerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

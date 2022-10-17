package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;


@Entity
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE questionentity SET isactive=false WHERE id=?")
@Table(name="questionentity")
public class QuestionEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	@CreationTimestamp
	private Date createdat;
	   
	@UpdateTimestamp
	private Date updatedat;
	
	private String question;
	
	private boolean isactive=true;
    
	@OneToOne 
	private Users user_id;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreatedat() {
		return createdat;
	}


	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}


	public Date getUpdatedat() {
		return updatedat;
	}


	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}


	public boolean isIsactive() {
		return isactive;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public Users getUser_id() {
		return user_id;
	}


	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}


	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}




	public QuestionEntity(long id, String description, Date createdat, Date updatedat, String question,
			boolean isactive, Users user_id) {
		super();
		this.id = id;
		this.description = description;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.question = question;
		this.isactive = isactive;
		this.user_id = user_id;
	}


	public QuestionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


}

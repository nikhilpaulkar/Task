package com.serviceinterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dto.AnswerDto;

import com.entity.AnswerEntity;


public interface AnswerServiceInterface 
{
	void addcomment(AnswerDto  answerDto,HttpServletRequest request);
	
	void deleteComment(Long id, HttpServletRequest request);
	List<AnswerEntity> getAll();
	AnswerDto getAnswerId(Long id);
	
	AnswerDto updatequestion(AnswerDto answerDto, Long id);
}

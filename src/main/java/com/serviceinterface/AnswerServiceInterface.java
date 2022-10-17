package com.serviceinterface;

import javax.servlet.http.HttpServletRequest;

import com.dto.AnswerDto;


public interface AnswerServiceInterface 
{
	void addcomment(AnswerDto  answerDto,HttpServletRequest request);



}

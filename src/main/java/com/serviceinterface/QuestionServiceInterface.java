package com.serviceinterface;

import javax.servlet.http.HttpServletRequest;

import com.dto.QuestionDto;
import com.entity.QuestionEntity;

public interface QuestionServiceInterface 
{
  QuestionEntity addquestion(QuestionDto questionDto, HttpServletRequest request);
}

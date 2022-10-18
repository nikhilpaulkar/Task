package com.serviceinterface;

import javax.servlet.http.HttpServletRequest;


import com.dto.QuestionDto;
import com.entity.QuestionEntity;

public interface QuestionServiceInterface 
{
  QuestionEntity addquestion(QuestionDto questionDto, HttpServletRequest request);
  
  QuestionDto getQuestionId(Long id,HttpServletRequest request);
  
  
  QuestionDto updatequestion(QuestionDto questionDto, Long id,HttpServletRequest request);
  
  
}

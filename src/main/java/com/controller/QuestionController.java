package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ErrorResponseDto;
import com.dto.QuestionDto;
import com.dto.SucessResponseDto;
import com.serviceinterface.QuestionServiceInterface;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
	@Autowired
	private QuestionServiceInterface questionServiceInterface;
	
	
	// Add Question 
	@PostMapping()
	public ResponseEntity<?>addquestion(@RequestBody QuestionDto questionDto, HttpServletRequest  request)
	{
		try
		{
			questionServiceInterface.addquestion(questionDto,request);
			return new ResponseEntity<>( new SucessResponseDto("question Added", "success", "success"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("not exit","fail"),HttpStatus.BAD_REQUEST);
		}
	}

}

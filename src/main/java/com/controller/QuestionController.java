package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ErrorResponseDto;
import com.dto.QuestionDto;
import com.dto.SucessResponseDto;

import com.serviceinterface.QuestionServiceInterface;

import comm.exception.ResourceNotFoundException;

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
	
	// Get By Id Only Admin can access
	@GetMapping("/{id}")
	public ResponseEntity<?> getQuestionById( @PathVariable Long id,HttpServletRequest request)
	{
		try 
		{
            QuestionDto questionDto=this.questionServiceInterface.getQuestionId(id,request);
			return new ResponseEntity<>(new SucessResponseDto("Success","Success", questionDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"Question id Not Found"),HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	//update Question by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateQuestionById(@RequestBody QuestionDto questionDto,@PathVariable Long id,HttpServletRequest request)
	{
	  try
		{
		  this.questionServiceInterface.updatequestion(questionDto,id,request);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
		
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid QuestionId..", "Or You can not access Only who create question that user can update question"),HttpStatus.NOT_FOUND);
		}
				
	}
	
	

		

}

package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AnswerDto;
import com.dto.ErrorResponseDto;

import com.dto.SucessResponseDto;

import com.entity.AnswerEntity;

import com.serviceinterface.AnswerServiceInterface;

import comm.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/answer")
public class AnswerController
{
	@Autowired
	private AnswerServiceInterface answerServiceInterface;
	
	// Add comment
	@PostMapping()
	public ResponseEntity<?>addquestion(@RequestBody AnswerDto answerDto, HttpServletRequest  request)
	{
	  try
		{
			answerServiceInterface.addcomment(answerDto,request);
			return new ResponseEntity<>( new SucessResponseDto("comment Added", "success", "success"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
				return new ResponseEntity<>(new ErrorResponseDto("Envalid Question Id","fail"),HttpStatus.BAD_REQUEST);
		}
		}
	
      
	// delete appointment by id 
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteAppointment(@PathVariable Long id,HttpServletRequest request)
	{
	  try
		{
			this.answerServiceInterface.deleteComment(id,request);
		    return new ResponseEntity<>(new SucessResponseDto("success","success","delete successfully"),HttpStatus.ACCEPTED);
		}catch( ResourceNotFoundException e)
			{	
				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(),"comment is  not found"),HttpStatus.BAD_REQUEST);
			}
		}
		
	// get all role id and permission id 
	@GetMapping
	public List<AnswerEntity>getall()
	{
	  return answerServiceInterface.getAll();
	}

    // Get Answer By Id
	@GetMapping("/{id}")
	public ResponseEntity<?> getAnswerById( @PathVariable Long id)
	{
		try 
		{
            AnswerDto answerDto=this.answerServiceInterface.getAnswerId(id);
			return new ResponseEntity<>(new SucessResponseDto("Success","Success", answerDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"Answer id Not Found"),HttpStatus.NOT_FOUND);
		}
	}
	
	
	//update Question by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateQuestionById(@RequestBody AnswerDto answerDto,@PathVariable Long id)
	{
	 try
		{
		  this.answerServiceInterface.updatequestion(answerDto,id);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
			
		}catch(Exception e)
		{
				return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid AnswerId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
		}
					
	}
}







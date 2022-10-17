package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AnswerDto;
import com.dto.ErrorResponseDto;

import com.dto.SucessResponseDto;
import com.serviceinterface.AnswerServiceInterface;

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
	}



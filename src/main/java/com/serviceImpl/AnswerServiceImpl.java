package com.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.AnswerDto;
import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.Users;
import com.repository.AnswerRepository;
import com.repository.QuestionRepository;
import com.repository.UsersRepository;
import com.serviceinterface.AnswerServiceInterface;
import com.webSecurity.JwtTokenUtil;

import comm.exception.ResourceNotFoundException;

@Service
public class AnswerServiceImpl implements AnswerServiceInterface 
{
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private JwtTokenUtil  jwtTokenUtil;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	// Add Comment
	@Override
	public void addcomment(AnswerDto answerDto, HttpServletRequest request)
	{
		final String header=request.getHeader("Authorization");
		String requestToken=header.substring(7);

		final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
		   
		Users user1=usersRepository.findByEmail(email);
		
		QuestionEntity questionid=questionRepository.findById(answerDto.getQuestionid()).orElseThrow(()->
		new ResourceNotFoundException("Envalid Question Id"));
		
		AnswerEntity entity= new AnswerEntity();
		entity.setComment(answerDto.getComment());
		entity.setUser_id(user1);
		
		entity.setQuestion_id(questionid);
		answerRepository.save(entity);
		
		
	}
	
	
	

}

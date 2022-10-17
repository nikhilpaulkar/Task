package com.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.QuestionDto;
import com.entity.QuestionEntity;
import com.entity.Users;
import com.repository.QuestionRepository;
import com.repository.UsersRepository;
import com.serviceinterface.QuestionServiceInterface;
import com.webSecurity.JwtTokenUtil;

@Service
public class QuestionServiceImpl implements QuestionServiceInterface
{
 @Autowired
 private QuestionRepository questionRepository;
 
 @Autowired
 private UsersRepository usersRepository;
 
 @Autowired
 private JwtTokenUtil jwtTokenUtil;

 // Add question
 @Override
 public QuestionEntity addquestion(QuestionDto questionDto, HttpServletRequest request)
 {
	final String header=request.getHeader("Authorization");
	String requestToken=header.substring(7);

	final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	Users user1=usersRepository.findByEmail(email);
	QuestionEntity entity= new QuestionEntity();
	entity.setDescription(questionDto.getDescription());
	entity.setQuestion(questionDto.getQuestion());
	entity.setUser_id(user1);
	return this.questionRepository.save(entity);
	 
	
 }
 
 
}

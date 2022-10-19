package com.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.QuestionDto;
import com.entity.QuestionEntity;
import com.entity.UserRoleEntity;
import com.entity.Users;
import com.repository.QuestionRepository;
import com.repository.UserRoleRepository;
import com.repository.UsersRepository;
import com.serviceinterface.QuestionServiceInterface;
import com.webSecurity.JwtTokenUtil;

import comm.exception.ResourceNotFoundException;

@Service
public class QuestionServiceImpl implements QuestionServiceInterface
{
 @Autowired
 private QuestionRepository questionRepository;
 
 @Autowired
 private UsersRepository usersRepository;
 
 @Autowired
 private JwtTokenUtil jwtTokenUtil;
 
 @Autowired
 private UserRoleRepository userRoleRepository;

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
	entity.setDate(questionDto.getDate());
	entity.setDraft(questionDto.isDraft());
	return this.questionRepository.save(entity);
	 
	
 }

 // Get Question Id Only Admin Can access
 @Override
 public QuestionDto getQuestionId(Long id,HttpServletRequest request) 
 {
	 QuestionEntity questionEntity= questionRepository.findById(id).orElseThrow(()-> 
	    
     new ResourceNotFoundException("not Found question Id.."));
    

	 final String header=request.getHeader("Authorization");
	 String requestToken=header.substring(7);
	 final String token=jwtTokenUtil.getUsernameFromToken(requestToken);
        
     Users userEntity=usersRepository.findByEmailContainingIgnoreCase(token);
     UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(userEntity.getId());
     String name=userRoleEntity.getTask().getRole().getRoleName();
     System.out.println("Role name:"+name);
   

	 if(name.equals("admin"))
	 {
		 QuestionDto questionDto=new QuestionDto();
		
		 questionDto.setDescription(questionEntity.getDescription());
		 
		 questionDto.setQuestion(questionEntity.getDescription());
		 questionDto.setUserid(userEntity.getId());
		
		 questionDto.setDate(questionEntity.getDate());
		 
		 return questionDto;
	 }
	else
	{
		throw new ResourceNotFoundException("Cannot Access.. Only Admin can get records ..");
	}
	
	
 }

 // Update Question Only who post question that user can update question
 @Override
 public QuestionDto updatequestion(QuestionDto questionDto, Long id,HttpServletRequest request)
 {
	 QuestionEntity  questionEntity=questionRepository.findById(id).orElseThrow(()-> 
     new ResourceNotFoundException("Not Found Question Id"));
	 
	 final String header=request.getHeader("Authorization");
	 String requestToken=header.substring(7);

	 final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
		   
	 Users user1=usersRepository.findByEmail(email);
	
	 if(user1.getId()==questionEntity.getUser_id().getId())
	 {
	 
	  questionEntity.setDescription(questionDto.getDescription());
	  questionEntity.setQuestion(questionDto.getQuestion());
	  questionEntity.setIsflag(true);
	
	  questionRepository.save(questionEntity);
	  return questionDto;
	 }
	 else
	 {
		 throw new ResourceNotFoundException("can not Access only Who create question that users can update question ");
	 }
	
 }

 
 
 
}

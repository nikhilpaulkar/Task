package com.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.AnswerDto;

import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.UserRoleEntity;
import com.entity.Users;
import com.repository.AnswerRepository;
import com.repository.QuestionRepository;
import com.repository.UserRoleRepository;
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
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
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
	
    // Delete comment only admin or User who have comment 
	@Override
	public void deleteComment(Long id, HttpServletRequest request)
	{
		  AnswerEntity ans=answerRepository.findById(id).orElseThrow(()-> 
		    
	     new ResourceNotFoundException("not Found answer Id.."));
	     
		 final String header=request.getHeader("Authorization");
		 String requestToken=header.substring(7);
		 final String token=jwtTokenUtil.getUsernameFromToken(requestToken);
	        
	     Users userEntity=usersRepository.findByEmailContainingIgnoreCase(token);
	     if(userEntity.getId()==ans.getUser_id().getId())
		  {
			answerRepository.deleteById(id);
		  }
	     else
	     {
	    	 
	     
	     UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(userEntity.getId());
	     String name=userRoleEntity.getTask().getRole().getRoleName();
	     System.out.println("Role name:"+name);

		 if(name.equals("admin"))
		 {
		   
			answerRepository.deleteById(id);
		 } 
		else
		{
				throw new ResourceNotFoundException("Cannot Access.. Only Admin can delete commit ..");
		}
	   }
	     
	}
     
	// get All Answer
	@Override
	public List<AnswerEntity> getAll()
	{
		return this.answerRepository.findAll();
		
	}
	
	
    // get Answer id
	@Override
	public AnswerDto getAnswerId(Long id)
	{
		AnswerEntity  answerEntity=answerRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("not found Answer id"));
		
		
		AnswerDto answerDto=new AnswerDto();
		
		answerDto.setComment(answerEntity.getComment());
		answerDto.setQuestionid(id);
		answerDto.setUserid(id);
		return answerDto;
		 
	}

	// Update Answer is stores in flag 
	@Override
	public AnswerDto updatequestion(AnswerDto answerDto, Long id)
	{
		AnswerEntity  answerEntity=answerRepository.findById(id).orElseThrow(()-> 
	    new ResourceNotFoundException("Not Found Answer Id"));
			
		answerEntity.setComment(answerDto.getComment());
		answerEntity.setIsflag(true);
		answerRepository.save(answerEntity);
		return answerDto;
		
	}
 
	
	
	
	

}

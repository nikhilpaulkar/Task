package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDto;
import com.entity.Users;
import com.repository.UsersRepository;

import com.serviceinterface.UserServiceInterface;
import com.utility.PasswordValidator;


@RestController

public class AuthController 
{
	@Autowired
	private UserServiceInterface userServiceImpl;
	
	@Autowired
	private UsersRepository userRepository;
	

	@PostMapping("/register")
	public ResponseEntity<?>createuser(@RequestBody UserDto userDto )throws Exception
	{
		   
		String email=userDto.getEmail();
		 
		if(PasswordValidator.isValidforEmail(email))
	    {
		 Users user=this.userRepository.findByEmail(userDto.getEmail());
			 if(user!=null)
			 {
				 return new ResponseEntity<>("user already register ",HttpStatus.NOT_ACCEPTABLE);
			 }
			 else
			 {
			  
			  this.userServiceImpl.createuser(userDto);
			  return new ResponseEntity<>("Register succesfully",HttpStatus.ACCEPTED);
			 }
		   }
	     	else
		  {
			return new ResponseEntity<>("email is not valid",HttpStatus.BAD_REQUEST);
		
		  }
 	 
	
	
	}	

}

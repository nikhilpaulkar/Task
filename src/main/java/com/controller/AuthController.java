package com.controller;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.dto.ErrorResponseDto;
import com.dto.LoggerDto;
import com.dto.SucessResponseDto;
import com.dto.TokenKeys;
import com.dto.UserDto;
import com.entity.Users;
import com.repository.UsersRepository;
import com.serviceImpl.AuthServiceImpl;
import com.serviceImpl.UsersServiceImpl;
import com.serviceinterface.LoggerServiceInerface;

import com.utility.PasswordValidator;
import com.webSecurity.JwtAuthRequest;
import com.webSecurity.JwtTokenUtil;


@RestController

public class AuthController 
{
	@Autowired
	private UsersServiceImpl userServiceImpl;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	  
	@Autowired
	private LoggerServiceInerface loggerServiceInterface;
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	  
	
    // Register API
	@PostMapping("/register")
	public ResponseEntity<?>createuser(@RequestBody UserDto userDto )throws Exception
	{
		   
		String email=userDto.getEmail();
		//String password=userDto.getPassword();
		 
		if(PasswordValidator.isValidforEmail(email))//&& PasswordValidator.isValid(password))
	    {
		 Users user=this.userRepository.findByEmailContainingIgnoreCase(email);
			 if(user!=null)
			 {
				 return new ResponseEntity<>("user already register ",HttpStatus.NOT_ACCEPTABLE);
			 }
			 else
			 {
			  
			  this.userServiceImpl.createuser(userDto);
			  return new ResponseEntity<> (new SucessResponseDto("Register succesfully", "success", "Register succesfully"),HttpStatus.ACCEPTED);
			 }
		   }
	     	else
		  {
			return new ResponseEntity<>("Email OR  password is not valid",HttpStatus.BAD_REQUEST);
		
		  }
 	 }
	
	
	 // Login API
	 @PostMapping("/login")
		public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequest authenticationRequest)
		throws Exception 
	    {

			try 
			{
	            Users user = userServiceImpl.FindByEmail(authenticationRequest.getEmail());
				
				
				if (authServiceImpl.comaparePassword(user.getPassword(), authenticationRequest.getPassword()));
				{
					UserDetails userDetails=this.authServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
				    String token = jwtTokenUtil.generateToken(userDetails);
				    LoggerDto logger = new LoggerDto();
					logger.setToken(token);
					Calendar calender = Calendar.getInstance();
					calender.add(Calendar.HOUR_OF_DAY, 5);
					logger.setExpiredAt(calender.getTime());
					loggerServiceInterface.createLogger(logger, user);
					return ResponseEntity.ok(new SucessResponseDto("Login successfully", "success", new TokenKeys(user.getId(),user.getName(),user.getEmail(), token)));
					
				}
				
			} catch (BadCredentialsException e) 
			{
				
				throw new Exception("invalid email or password");
				
			} catch (Exception e)
			{
				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "user not found "), HttpStatus.NOT_FOUND);
			}

		}
	   

	   

}

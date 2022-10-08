package com.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.entity.Users;
import com.repository.AuthRepository;
import com.serviceinterface.AuthInterface;

public class AuthServiceImpl implements AuthInterface
{
  @Autowired
  private AuthRepository authRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
  {
	  Users user = null;
	  user=authRepository.findByEmail(email);
	  if(user==null)
	  {
		 throw new UsernameNotFoundException("user not found with"+email);
	  }
	  
	 return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());


	
  }


  @Override
  public boolean comaparePassword(String email, String hashpassword) 
  {
	
	return passwordEncoder.matches(hashpassword, hashpassword);
  }
  
  
  
  
  
  
  
  
  
  
  
  
}

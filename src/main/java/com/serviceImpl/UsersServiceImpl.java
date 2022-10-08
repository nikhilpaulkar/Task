package com.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dto.UserDto;
import com.entity.Users;
import com.serviceinterface.UserServiceInterface;

@Service
public class UsersServiceImpl implements UserServiceInterface 
{

	@Override
	public Users createuser(UserDto userDto)
	{
		
		return null;
	}

	public UserDetails loadUserByUsername(String email) 
	{
		
		return null;
	}

}

package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.UserDto;
import com.entity.Users;

import com.repository.UsersRepository;
import com.serviceinterface.IUserListDto;
import com.serviceinterface.UserServiceInterface;
import com.utility.Pagination;

import comm.exception.ResourceNotFoundException;

@Service
public class UsersServiceImpl implements UserServiceInterface 
{
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
    // Add User
	@Override
	public Users createuser(UserDto userDto)
	{
		Users user=new Users();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return this.userRepository.save(user);
		
	}

	public UserDetails loadUserByUsername(String email) 
	{
		
		return null;
	}

	public Users FindByEmail(String email)
	{
		Users user =this.userRepository.findByEmail(email);
		return  user;
		
		
	}

	// Get all Users With Pagination 
	@Override
	public Page<IUserListDto> getAllUsers(String search, String pageNumber, String pageSize) 
	{
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return userRepository.findByOrderById(pagable,IUserListDto.class);
		}
		else
		{
			return  userRepository.findByName(search,pagable,IUserListDto.class);
		}
		
	}
	
    // Get By User Id 
	@Override
	public UserDto getUserId(Long id) 
	{
	
		Users userEntity=userRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("user not found with id "));
		
		UserDto userDto=new UserDto();
		userDto.setName(userEntity.getName());
		userDto.setEmail(userEntity.getEmail());
		return userDto;
	}

	// update user by id 
	@Override
	public UserDto updateUser(UserDto userDto, Long id)
	{
      Users userEntity=userRepository.findById(id).orElseThrow(()-> 
      new ResourceNotFoundException("Not Found User Id"));
		
	  userEntity.setEmail(userDto.getEmail());
	  userEntity.setName(userDto.getName());
	  String password=passwordEncoder.encode(userDto.getPassword());
	  userEntity.setPassword(password);
		
	   userRepository.save(userEntity);
	   return userDto;
		
	}

	// delete user by id
	@Override
	public void deleteUser(Long id)
	{
		this.userRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("User Not Found With Id :"+id));
		this.userRepository.deleteById(id);
		
	}

}

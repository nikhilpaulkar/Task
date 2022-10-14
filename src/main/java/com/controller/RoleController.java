package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ErrorResponseDto;
import com.dto.RoleDto;
import com.dto.SucessResponseDto;
import com.serviceinterface.IRoleListDto;
import com.serviceinterface.RoleServiceInterface;

import comm.exception.ResourceNotFoundException;



@RestController
@RequestMapping("/role")
public class RoleController 
{
	@Autowired
	private RoleServiceInterface roleServiceInterface;
	
	// add role 
	@PostMapping()
	public ResponseEntity<?>addrole(@RequestBody RoleDto roleDto)
	{
	  try
		{
		  roleServiceInterface.addrole(roleDto);
		  return new ResponseEntity<>(new SucessResponseDto("success","success","successfully add role"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
		  return new ResponseEntity<>(new ErrorResponseDto( "role Already exit ","role already exxit"),HttpStatus.BAD_REQUEST);
		}
			
			
	}
	
	
	// get all roles with pagination
	@GetMapping()
	public ResponseEntity<?> getAllusers(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	{
		
		Page<IRoleListDto> entity= roleServiceInterface.getAllrole(search,pageNumber,pageSize);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(entity.getContent(), HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
	    }
	}
	
	// get by id 
	@GetMapping("/{id}")
	public ResponseEntity<?>getRoleid(@PathVariable Long id)
	{
		try 
		{
            RoleDto roleDto=this.roleServiceInterface.getRoleId(id);
			return new ResponseEntity<>(new SucessResponseDto("Success","Success", roleDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"User Not Found"),HttpStatus.NOT_FOUND);
		}
	   }
	
	// update by role id 
	@PutMapping("/{id}")
	public ResponseEntity<?>updateByRoleId(@RequestBody RoleDto roleDto,@PathVariable Long id)
	{
		try
		{
			
		  this.roleServiceInterface.updaterole(roleDto,id);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
	
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid RoleId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
		}
			
	}
	
	// delete by role id 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleterole(@PathVariable Long id)
	{

		try 
		{
			this.roleServiceInterface.deleteRole(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"role Not Found"),HttpStatus.NOT_FOUND);
	    }
	}
	


}

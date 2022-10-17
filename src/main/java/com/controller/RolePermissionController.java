package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.SucessResponseDto;
import com.entity.RolePermissionEntity;
import com.payload.RolePermissionRequest;
import com.serviceinterface.RolePermissionServiceInterface;

@RestController
@RequestMapping("/rolepermission")
public class RolePermissionController  
{
	@Autowired
	private RolePermissionServiceInterface rolePermissionServiceInterface;
	
	// add permission id and role id
	@PostMapping
	private ResponseEntity<?> add(@RequestBody RolePermissionRequest rolePermissionRequest)
	{
	   try
			{
				this.rolePermissionServiceInterface.add(rolePermissionRequest);
				return new ResponseEntity<>(new SucessResponseDto("Success", "Success", rolePermissionRequest),HttpStatus.OK);
			}catch(Exception e)
			{
				return new ResponseEntity<>("not found role id or permission id ",HttpStatus.NOT_FOUND);
			}
			
			
	}

	// get all role id and permission id 
	@GetMapping
	public List<RolePermissionEntity>getall()
	{
		return rolePermissionServiceInterface.getAll();
	}
	
	// update role id and permission id
	@PutMapping
	public ResponseEntity<?> update(@RequestBody RolePermissionRequest rolePermissionRequest)
	{
		try
			{
				this.rolePermissionServiceInterface.updateRolePermission(rolePermissionRequest);
				return new ResponseEntity<>(new SucessResponseDto("Update Successfully", "Success", "updated"),HttpStatus.ACCEPTED);	
			}catch(Exception e)
			{
				return new ResponseEntity<>("Not Found role id or permission id ",HttpStatus.NOT_FOUND);	
			}
			
	}
	
	// delete role id and permission id 
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody RolePermissionRequest rolePermissionRequest)
	{
		try
			{
				this.rolePermissionServiceInterface.deleteRolePermission(rolePermissionRequest);
				return new ResponseEntity<>(new SucessResponseDto("Delete Successfully", "Success", "deleted"),HttpStatus.ACCEPTED);
					
			}catch(Exception e)
			{
				return new ResponseEntity<>("Invalid role id or permission id ",HttpStatus.NOT_FOUND);
			}
			
	}
	
}

package com.serviceinterface;

import org.springframework.data.domain.Page;

import com.dto.PermissionDto;
import com.entity.PermissionEntity;

public interface PermissionServiceInterface 
{
	PermissionEntity addPemission(PermissionDto permissionDto);
	
	
	Page<PermissionListDto> getAllPermission(String search, String pageNumber, String pageSize);
      
	PermissionDto getPermissionById(Long id);
  
    PermissionDto updatepermission(PermissionDto permissionDto, Long id);
    
    void deletepermissionbyid(Long id);

}

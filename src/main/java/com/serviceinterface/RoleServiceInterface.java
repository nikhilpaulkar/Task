package com.serviceinterface;



import org.springframework.data.domain.Page;

import com.dto.RoleDto;


public interface RoleServiceInterface 
{
	void addrole(RoleDto roleDto);

	Page<IRoleListDto> getAllrole(String search, String pageNumber, String pageSize);

	RoleDto getRoleId(Long id);

	RoleDto updaterole(RoleDto roleDto, Long id);

	void deleteRole(Long id);
	
	
}

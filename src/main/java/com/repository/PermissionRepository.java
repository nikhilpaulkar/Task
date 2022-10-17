package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.PermissionEntity;
import com.serviceinterface.PermissionListDto;
@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>
{

	Page<PermissionListDto> findByOrderById(Pageable pagable, Class<PermissionListDto> class1);

	Page<PermissionListDto> findByActionName(String search, Pageable pagable, Class<PermissionListDto> class1);
	
	
	

}

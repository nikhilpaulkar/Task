package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Users;
import com.serviceinterface.IUserListDto;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> 
{

	

	Users findByEmailContainingIgnoreCase(String email);

	Users findByEmail(String email);

	Page<IUserListDto> findByOrderById(Pageable pagable, Class<IUserListDto> class1);

	Page<IUserListDto> findByName(String search, Pageable pagable, Class<IUserListDto> class1);

}

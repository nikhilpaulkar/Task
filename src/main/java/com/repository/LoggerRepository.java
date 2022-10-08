package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.LoggerEntity;

public interface LoggerRepository extends JpaRepository<LoggerEntity, Long>
{

	LoggerEntity findByToken(String requestTokenHeader);

}

package com.serviceinterface;

import com.dto.LoggerDto;
import com.entity.LoggerEntity;
import com.entity.Users;

public interface LoggerServiceInerface 
{
	public void createLogger(LoggerDto loggerdto , Users user);

	public LoggerEntity getLoggerDetail(String requestTokenHeader);
}

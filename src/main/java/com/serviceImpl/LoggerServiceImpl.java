package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.LoggerDto;
import com.entity.LoggerEntity;
import com.entity.Users;
import com.repository.LoggerRepository;
import com.serviceinterface.LoggerServiceInerface;

@Service
public class LoggerServiceImpl implements LoggerServiceInerface
{
	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public void createLogger(LoggerDto loggerdto, Users user)
	{
      LoggerEntity logger=new LoggerEntity();
      logger.setUserid(user);
      logger.setToken(loggerdto.getToken());
      logger.setExpiredat(loggerdto.getExpiredAt());
      loggerRepository.save(logger);

		
	}

	@Override
	public LoggerEntity getLoggerDetail(String requestTokenHeader)
	{
	   LoggerEntity logger= loggerRepository.findByToken(requestTokenHeader);
	   return logger;
	   
		
	}

}

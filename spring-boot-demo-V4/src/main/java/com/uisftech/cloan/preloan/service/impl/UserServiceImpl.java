package com.uisftech.cloan.preloan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.respository.UserRespository;
import com.uisftech.cloan.preloan.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRespository userRespository;
	@Override
	public User findUser(String id) {
		LOGGER.info("调用查找用户的方法： {findUser} ");
		return userRespository.findOne(id);

	}

}

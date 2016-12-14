package com.uisftech.cloan.preloan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.respository.UserRespository;
import com.uisftech.cloan.preloan.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRespository userRespository;
	@Override
	public User findUser(String id) {
		return userRespository.findOne(id);

	}

}

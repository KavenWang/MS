package com.uisftech.cloan.preloan.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.dto.UserAddressDTO;
import com.uisftech.cloan.preloan.respository.UserRespository;
import com.uisftech.cloan.preloan.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRespository userRespository;
	@PersistenceContext
	private  EntityManager entityManager;

	@Override
	public User findUser(Long id) {
		LOGGER.info("调用查找用户的方法： {findUser} ");
		return userRespository.findOne(id);

	}
	@Override
	public List<UserAddressDTO> findUserAddressByUsername(String username) {
		List<UserAddressDTO> list = userRespository.findUserAddressByUsername(username);
		/*String hql = "SELECT com.uisftech.preloan.dto.UserAddressDTO(u.username,u.password,a.province,a.city,a.stree) FROM User u,Address a WHERE u.id = a.userid AND u.username ="+username;
		List<Object[]> list = userRespository.findByHQL(hql);*/
		return list;
	}
	@Override
	public List<Object[]> findBySQL(String username) {
		String sql = "select * from t_user where username= '"+username+"'";
		List<Object[]> userList = userRespository.findBySQL(sql);
		return userList;
	}

}

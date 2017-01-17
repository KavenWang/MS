package com.uisftech.cloan.preloan.service;

import java.util.List;

import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.dto.UserAddressDTO;

public interface IUserService {

	User findUser(Long id);

	public List<UserAddressDTO> findUserAddressByUsername(String username);

	List<Object[]> findBySQL(String username);

}

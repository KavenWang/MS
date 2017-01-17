package com.uisftech.cloan.preloan.respository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.dto.UserAddressDTO;
import com.uisftech.common.base.BaseRepository;
public interface UserRespository extends BaseRepository<User,Long>{

	public User findByUsername(String username);

	@Query("SELECT UserAddressDTO(username,password,province,city,stree) FROM User u,Address a WHERE u.id = a.userid AND u.username =: username")
	public List<UserAddressDTO> findUserAddressByUsername(@Param(value = "username")String username);

}

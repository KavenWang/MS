package com.uisftech.cloan.preloan.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uisftech.cloan.preloan.domain.User;
public interface UserRespository extends JpaRepository<User,String>{

	public User findByUsername(String username);
}

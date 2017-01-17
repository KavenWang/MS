package com.uisftech.cloan.preloan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uisftech.Application;
import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.respository.UserRespository;
import com.uisftech.cloan.preloan.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
	@Autowired
	private UserRespository userRespository;
	@Autowired
	private IUserService userService;

	@Test
	public void testSave(){
		User u = new User();
		u.setId("1");
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");

		userRespository.save(u);
	}
	@Test
	public void testFindUserById(){
		userRespository.findOne("11");
		userService.findUser("11");
	}

}

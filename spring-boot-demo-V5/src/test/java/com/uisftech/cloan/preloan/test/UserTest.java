package com.uisftech.cloan.preloan.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uisftech.Application;
import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.respository.AddressRespository;
import com.uisftech.cloan.preloan.respository.UserRespository;
import com.uisftech.cloan.preloan.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
	@Autowired
	private UserRespository userRespository;
	@Autowired
	private AddressRespository addressRespository;
	@Autowired
	private IUserService userService;

	@Test
	public void testSave(){
		User u = new User();
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");

		userRespository.save(u);
	}
	@Test
	public void testFindUserById(){
		userRespository.findOne(1l);
		userService.findUser(1l);
	}

	@Test
	public void testFindBySQL(){
		String sql = "select username,password from t_user";
		List<Object[]> list = userRespository.findBySQL(sql);
		for(Object obj[] : list){
			System.out.println(obj[0]+"----"+obj[1]);
		}

	}

	@Test
	public void testUpdateBySQL(){
		String sql = "update t_user set username='wangwei' where id=?";
		int i = userRespository.updateBySQL(sql, 1);
		System.out.println(i);
	}

	@Test
	public void testUpdateByHQL(){
		String sql = "UPDATE User u SET u.username='kaven' where u.id = ?";
		Long id = 2L;
		int i = userRespository.updateByHql(sql, id);
		System.out.println(i);
	}
}

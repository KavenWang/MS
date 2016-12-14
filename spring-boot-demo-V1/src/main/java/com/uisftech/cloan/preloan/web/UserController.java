package com.uisftech.cloan.preloan.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/findUser/{id}",method=RequestMethod.GET)
	public User findUser(@PathVariable String id){
		return userService.findUser(id);
	}
}

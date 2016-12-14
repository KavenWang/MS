package com.uisftech.cloan.preloan.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uisftech.cloan.preloan.common.CommonCode;
import com.uisftech.cloan.preloan.common.ResponseData;
import com.uisftech.cloan.preloan.domain.User;
import com.uisftech.cloan.preloan.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value="/findUser/{id}",method=RequestMethod.GET)
	public User findUser(@PathVariable String id){
		return userService.findUser(id);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/findUser1/{id}",method=RequestMethod.GET)
	public ResponseEntity findUser1(@PathVariable String id){
		User u = userService.findUser(id);
		ResponseData ruselt = new ResponseData(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMessage(), u);
		return new ResponseEntity<>(ruselt,HttpStatus.OK);
	}

}

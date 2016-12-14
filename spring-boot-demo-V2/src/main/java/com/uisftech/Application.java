package com.uisftech;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uisftech.cloan.preloan.domain.User;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableAutoConfiguration
@ComponentScan({"com.uisftech"})
public class Application {
	@RequestMapping("/")
	String index() {
		return "www.yoodb.com";
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
package com.uisftech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uisftech.common.base.BaseRepositoryFactoryBean;

@EnableJpaRepositories(basePackages = {"com.uisftech"},repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@SpringBootApplication
public class Application {
	@RequestMapping("/")
	String index() {
		return "www.yoodb.com";
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
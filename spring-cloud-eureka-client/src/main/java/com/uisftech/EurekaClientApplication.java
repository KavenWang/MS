package com.uisftech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient
@EnableAutoConfiguration
@ComponentScan({"com.uisftech"})
public class EurekaClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
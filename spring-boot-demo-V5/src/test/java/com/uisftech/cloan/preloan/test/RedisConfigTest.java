package com.uisftech.cloan.preloan.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.uisftech.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisConfigTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Test
	public void test1(){

		stringRedisTemplate.opsForValue().set("name", "wangkaven");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("name"));

	}
}

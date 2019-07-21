package com.lease.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.lease.LeaseApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=LeaseApplication.class)
public class RedisTest {
	@Autowired
	RedisTemplate<String, Object> RedisTemplate;
	RedisAutoConfiguration re;
	@Test
	public void testRedis() {
		RedisTemplate.opsForValue().set("testRedisTemplate", "hello redis");
	}
}

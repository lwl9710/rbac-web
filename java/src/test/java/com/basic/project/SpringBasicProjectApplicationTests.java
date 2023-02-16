package com.basic.project;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SpringBasicProjectApplicationTests {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void testRedis() {
		ValueOperations<String, Object> redisClient = redisTemplate.opsForValue();
		redisClient.set("甜甜的水果", "味道棒极了");
		System.out.println(redisClient.get("甜甜的水果"));
	}

	@Test
	void contextLoads() {
	}

}

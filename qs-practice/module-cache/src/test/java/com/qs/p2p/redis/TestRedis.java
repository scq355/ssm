package com.qs.p2p.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by scq on 2018-01-25 14:27:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-redis.xml" })
public class TestRedis {
	@Autowired
	private RedisTemplate<String, String> template;

	/**
	 * http://blog.csdn.net/wlwlwlwl015/article/details/52863821
	 * RedisSerializer默认使用的是JdkSerializationRedisSerializer
	 */
	@Test
	public void testFirst() {
		// \xac\xed\x00\x05t\x00\x04name
		template.opsForValue().set("name", "scq355");
		// get username
		System.out.println(template.opsForValue().get("name"));
	}

	@Test
	public void testBound(){
		BoundValueOperations<String, String> boundValueOps = template.boundValueOps("school");
		boundValueOps.set("scq");
		System.out.println(boundValueOps.get());
		boundValueOps.set("nwpu");
		System.out.println(boundValueOps.get());
	}
}

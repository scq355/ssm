package com.qs.p2p.redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by scq on 2018-01-24 13:21:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/spring-redis.xml")
public class RedisTest {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	private JedisConnection jedisConnection;


	@Before
	public void setBefore() {
		jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();
	}

	@Test
	public void testGet() {
	}
}

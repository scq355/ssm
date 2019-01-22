package com.sangame.redis;

import com.sangame.cache.impl.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by admin on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring.xml")
public class RedisTest extends AbstractJUnit4SpringContextTests{
    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    RedisService redisService;

    @Test
    public void testGet() {
        logger.info("======================" + redisService.get("filter.day.sensitive.word.max9527"));
        logger.info("======================" + redisService.setnx("scq", "456543324", 3000));
        logger.info("======================" + redisService.get("scq"));
    }
}

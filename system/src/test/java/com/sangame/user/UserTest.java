package com.sangame.user;

import com.sangame.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by nudtn on 2017/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring.xml")
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    UserService userService;

    @Test
    public void getUser() {
        logger.info(userService.get(1).toString());
    }
}

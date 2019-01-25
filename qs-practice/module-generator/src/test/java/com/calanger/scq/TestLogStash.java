package com.calanger.scq;

import com.qs.p2p.generator.ApplicationGenerator;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationGenerator.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLogStash {

    public static final Logger logger= Logger.getLogger(TestLogStash.class);

    @Test
    public void testLog4j() {
        logger.debug("This is a debug message!");
        logger.info("This is info message!");
        logger.warn("This is a warn message!");
        logger.error("This is error message!");
        try{
            System.out.println(5/0);
        }catch(Exception e){
            logger.error(e);
        }
    }
}

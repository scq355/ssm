import com.sangame.datafilter.redis.RedisHelper;
import com.sangame.datafilter.service.FilterVocabularyService;
import com.sangame.datafilter.util.SpringBeanManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class VocabularyTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	private FilterVocabularyService vocabularyService;

	@Before
	public void initSpring(){
		// 初始化spring
		SpringBeanManager.initContext(applicationContext);
		RedisHelper.initJedisPool();
	}

    //@Test
   /* public void test1() throws Exception {
    	boolean test = vocabularyService.isContaintSensitiveWord("应该没楼凤交友有十字弩吧", 2);
    	System.out.println("res:" + test);
    }*/

    @Test
    public void test2() throws Exception {
    	Set<String> test = vocabularyService.getSensitiveWord("应该电狗没楼凤交友有十狗粮字弩吧", 2);
    	System.out.println("res:" + test);
    }

}

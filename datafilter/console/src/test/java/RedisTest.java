import com.sangame.datafilter.common.persistence.model.FilterVocabulary;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;
import com.sangame.datafilter.util.SpringBeanManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class RedisTest extends AbstractJUnit4SpringContextTests{

	@Before
	public void initSpring(){
		// 初始化spring
		SpringBeanManager.initContext(applicationContext);
		RedisHelper.initJedisPool();
	}
	
	@Test
    public void testString() throws Exception {
    	String t = "aaaassseee";
    	RedisHelper.setString("testString", t, RedisCacheTime.TEN_MINUTE_STORE);
    	System.out.println("res:" + RedisHelper.getString("testString"));
    }
    
	@Test
    public void testObjectSerialize() {
		FilterVocabulary filterVocabulary = new FilterVocabulary();
		filterVocabulary.setId(10086L);
		filterVocabulary.setContent("testContent 测试内容");
		filterVocabulary.setCreator("叶青锋");
		filterVocabulary.setType(1);
		filterVocabulary.setDeleteFlag(0);
    	RedisHelper.setObjectBySerialize("testObjectSerialize", filterVocabulary, RedisCacheTime.TEN_MINUTE_STORE);
    	FilterVocabulary ret = (FilterVocabulary)RedisHelper.getObjectByDeserialize("testObjectSerialize");
    	System.out.println("testObjectSerialize:" + ret);
    }
	
	@Test
    public void testJsonObject() {
		FilterVocabulary filterVocabulary = new FilterVocabulary();
		filterVocabulary.setId(10086L);
		filterVocabulary.setContent("testContent 测试内容");
		filterVocabulary.setCreator("叶青锋");
		filterVocabulary.setType(1);
		filterVocabulary.setDeleteFlag(0);
    	RedisHelper.setObjectByJSON("testJsonObject", filterVocabulary, RedisCacheTime.TEN_MINUTE_STORE);
    	FilterVocabulary ret = (FilterVocabulary)RedisHelper.getObjectByClass("testJsonObject", FilterVocabulary.class);
    	System.out.println("testJsonObject:" + ret);
    }
	
	@Test
    public void testObjectSerialize2() {
		List<FilterVocabulary> list = new ArrayList<FilterVocabulary>();
		FilterVocabulary filterVocabulary = new FilterVocabulary();
		filterVocabulary.setId(10086L);
		filterVocabulary.setContent("testContent 测试内容");
		filterVocabulary.setCreator("叶青锋");
		filterVocabulary.setType(1);
		filterVocabulary.setDeleteFlag(0);
		filterVocabulary.setId(10087L);
		list.add(filterVocabulary);
		FilterVocabulary filterVocabulary2 = new FilterVocabulary();
		filterVocabulary2.setContent("测试内容222");
		filterVocabulary2.setCreator("叶青锋");
		filterVocabulary2.setType(2);
		filterVocabulary2.setDeleteFlag(1);
		list.add(filterVocabulary2);
    	RedisHelper.setObjectBySerialize("testObjectSerialize2", list, RedisCacheTime.TEN_MINUTE_STORE);
    	@SuppressWarnings("unchecked")
		List<FilterVocabulary> ret = (List<FilterVocabulary>)RedisHelper.getObjectByDeserialize("testObjectSerialize2");
    	System.out.println("testObjectSerialize2:" + ret);
    }


} 

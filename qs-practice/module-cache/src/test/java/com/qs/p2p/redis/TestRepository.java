package com.qs.p2p.redis;

import com.qs.p2p.redis.bean.LoginInfo;
import com.qs.p2p.redis.repository.LoginInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by scq on 2018-01-25 16:12:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-redis.xml" })
public class TestRepository {
	@Autowired
	private LoginInfoRepository loginInfoRepository;

	@Test
	public void testRepositorySave() {
		List<LoginInfo> loginInfoList = new ArrayList<>();
		loginInfoList.add(new LoginInfo("scq355", "nwpuscq", "localhost", "127.0.0.1", "2018-01-01 00:00:00"));
		loginInfoList.add(new LoginInfo("scq345", "nwpuscq", "localhost", "127.0.0.1", "2018-01-01 00:00:00"));
		loginInfoList.add(new LoginInfo("scq123", "nwpuscq", "localhost", "127.0.0.1", "2018-01-01 00:00:00"));
		for (LoginInfo entity : loginInfoList) {
			loginInfoRepository.save(entity);
		}
	}

	@Test
	public void testRepositoryGet() {
		Iterator<LoginInfo> iterator = loginInfoRepository.findAll().iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().toString());
		}
	}
}

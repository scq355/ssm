package com.qs.p2p.redis.repository;

import com.qs.p2p.redis.utils.MyKeyspaceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by scq on 2018-01-25 16:11:42
 */
@Component(value = "loginInfoRepositoryImpl")
@EnableRedisRepositories(keyspaceConfiguration = MyKeyspaceConfiguration.class)
public class LoginInfoRepositoryImpl {
	@Autowired
	private LoginInfoRepository loginInfoRepository;
}

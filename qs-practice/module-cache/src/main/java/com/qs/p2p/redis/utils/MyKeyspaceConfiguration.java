package com.qs.p2p.redis.utils;

import com.qs.p2p.redis.bean.LoginInfo;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

import java.util.Collections;

/**
 * Created by scq on 2018-01-25 16:57:03
 */
public class MyKeyspaceConfiguration extends KeyspaceConfiguration {
	@Override
	protected Iterable<KeyspaceSettings> initialConfiguration() {
		return Collections.singleton(new KeyspaceSettings(LoginInfo.class, "kkk"));
	}
}

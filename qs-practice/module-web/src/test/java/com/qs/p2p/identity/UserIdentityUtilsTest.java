package com.qs.p2p.identity;

import com.qs.p2p.utils.UserIdentity;
import com.qs.p2p.utils.UserIdentityUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by scq on 2018-01-18 13:39:10
 */
public class UserIdentityUtilsTest {

	UserIdentity userIdentity = new UserIdentity();

	private static String serializedUserInfo;

	@Before
	public void testIdentitySerialize() {
		userIdentity.setAvatar("scq355");
		userIdentity.setMobile("18710755118");
		userIdentity.setUsername("scq355");
		userIdentity.setId(12);
		userIdentity.setLastVisitTime(new Date());
		serializedUserInfo = UserIdentityUtils.serialize(userIdentity);
		System.out.println(serializedUserInfo);
	}

	@Test
	public void testIdentityUnSerialize() {
		UserIdentity userIdentityRes = UserIdentityUtils.unserialize(serializedUserInfo);
		System.out.println(userIdentityRes.getLastVisitTime()
				+ userIdentityRes.getAvatar()
				+ userIdentityRes.getId()
				+ userIdentityRes.getMobile()
				+ userIdentityRes.getUsername());
	}


}

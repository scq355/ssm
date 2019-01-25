package com.qs.p2p.identity;

import com.qs.p2p.constant.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Created by scq on 2018-01-18 15:42:50
 */
public class PasswordTest {

	private static final String PASSWORD = "scq355545454";

	@Test
	public void testEncode() {
		String resPWD = DigestUtils.md5Hex(DigestUtils.sha1Hex(PASSWORD.trim()) + Constants.PASSWORD_KEY);
		System.out.println(resPWD);
	}
}

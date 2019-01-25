package com.qs.p2p.utils;

import com.qs.p2p.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by scq on 2018-01-17 11:37:03
 */
public class UserIdentityUtils {
	private static final String DATE_PATTERN = "yyyyMMdd HH:mm:ss";

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

	/**
	 * 序列化
	 * @param userIdentity：用户凭证
	 */
	public static String serialize(UserIdentity userIdentity) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(userIdentity.getId()).append(",");
		stringBuilder.append(userIdentity.getUsername()).append(",");
		stringBuilder.append(userIdentity.getMobile()).append(",");
		stringBuilder.append(userIdentity.getAvatar()).append(",");
		String visitTime = DATE_FORMAT.format(userIdentity.getLastVisitTime());
		stringBuilder.append(visitTime).append(",");

		return BlowfishUtils.encrypt(stringBuilder.toString(), Constants.SECRET_KEY);
	}

	/**
	 * 反序列化
	 * @param value：参数
	 */
	public static UserIdentity unserialize(String value) {
		try {
			String text = BlowfishUtils.decrypt(value, Constants.SECRET_KEY);
			String[] stringArray = text.split(",");
			UserIdentity userIdentity = new UserIdentity();
			userIdentity.setId(Integer.parseInt(stringArray[0]));
			userIdentity.setUsername(stringArray[1]);
			userIdentity.setMobile(stringArray[2]);
			userIdentity.setAvatar(stringArray[3]);
			userIdentity.setLastVisitTime(DATE_FORMAT.parse(stringArray[4]));
			return userIdentity;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * 获取凭证
	 */
	public static UserIdentity getUserIdentity(HttpServletRequest request) {
		return (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
	}

	private UserIdentityUtils() {
	}

}

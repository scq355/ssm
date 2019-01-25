package com.qs.p2p.constant;

import com.qs.p2p.utils.Config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by scq on 2018-01-17 11:34:13
 */
public final class Constants {
	public static final String BASE_URL;
	public static final String BASE_DOMAIN;
	public static final String STATIC_DOMAIN;
	public static final String HOME_DOMAIN;

	public static final String COOKIE_NAME;
	public static final String COOKIE_DOMAIN;
	public static final String COOKIE_PATH;

	public static final String SECRET_KEY;
	public static final int SESSION_TIMEOUT;

	public static final String USER_IDENTITY_KEY;
	public static final String PASSWORD_KEY = Config.getConfig().get("admin.password.key");

	public static final String VALIDATE_CODE;

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final int PAGE_SIZE = 12; // 分页数据条数

	static {
		BASE_URL = Config.getConfig().get("admin.baseUrl");
		BASE_DOMAIN = Config.getConfig().get("admin.base.domain");
		STATIC_DOMAIN = Config.getConfig().get("admin.static.domain");
		HOME_DOMAIN = Config.getConfig().get("admin.home.domain");

		COOKIE_NAME = Config.getConfig().get("admin.cookie.name");
		COOKIE_DOMAIN = Config.getConfig().get("admin.cookie.domain");
		COOKIE_PATH = Config.getConfig().get("admin.cookie.path");

		SECRET_KEY = Config.getConfig().get("admin.secret.key");
		SESSION_TIMEOUT = Integer.parseInt(Config.getConfig().get("admin.session.timeout"));

		VALIDATE_CODE = "_p2p_admin_code";

		USER_IDENTITY_KEY = Config.getConfig().get("admin.user.identity.key");
	}
}

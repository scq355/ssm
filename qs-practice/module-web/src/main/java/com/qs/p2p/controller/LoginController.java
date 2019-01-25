package com.qs.p2p.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.qs.p2p.constant.Constants;
import com.qs.p2p.model.User;
import com.qs.p2p.service.UserService;
import com.qs.p2p.utils.UserIdentity;
import com.qs.p2p.utils.UserIdentityUtils;
import com.qs.p2p.web.CookieUtils;
import com.qs.p2p.web.RequestUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by scq on 2018-01-17 13:21:49
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String show() {
		return "/login/login";
	}

	/**
	 * 登录校验
	 * 0: success
	 * 1: user not exists
	 * 2: incorrect password
	 * 3: empty username or password or verify code
	 * 4: verify code incorrect
	 * 5: unknown error
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> check(String userName, String password, String verify,
									 HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> map = Maps.newHashMap();
		if (Strings.isNullOrEmpty(userName.trim()) || Strings.isNullOrEmpty(password.trim()) || Strings.isNullOrEmpty(verify)) {
			map.put("code", "3");
			map.put("msg", "empty username or password or verify code");
			removeCookie(response);
			return map;
		}

		if (!isVerifyCorrect(verify, request)) {
			map.put("code", "4");
			map.put("msg", "verify code incorrect");
			removeCookie(response);
			return map;
		}

		User userVO = new User();
		userVO.setUserName(userName);
		User user;
		try {
			user = userService.get(userVO);
			if (user == null) {
				map.put("code", "1");
				map.put("msg", "user not exists");
				removeCookie(response);
				return map;
			}
			String secretPwd = DigestUtils.md5Hex(DigestUtils.sha1Hex(password.trim()) + Constants.PASSWORD_KEY);
			if (!user.getPassword().equals(secretPwd)) {
				map.put("code", "2");
				map.put("msg", "incorrect password");
				removeCookie(response);
				return map;
			}
		} catch (Exception e) {
			LOGGER.error("LOGIN CHECK:", e);
			map.put("code", "5");
			map.put("msg", "unknown error");
			removeCookie(response);
			return map;
		}
		Date date = new Date();

		try {
			User userEntity = new User();
			user.setDateLogin(date);
			user.setIpLogin(RequestUtils.getClientIp(request));
			userService.update(userEntity, user.getId());
		} catch (Exception e) {
			LOGGER.error("LOGIN ERROR:", e);
			map.put("code", "5");
			map.put("msg", "unknown error");
			removeCookie(response);
			return map;
		}

		UserIdentity userIdentity = new UserIdentity();
		userIdentity.setId(user.getId());
		userIdentity.setUsername(user.getUserName());
		userIdentity.setMobile(user.getPhoneNumber());
		userIdentity.setAvatar(user.getAvatar());
		userIdentity.setLastVisitTime(date);

		CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME, UserIdentityUtils.serialize(userIdentity));

		map.put("code", "0");
		map.put("msg", "success");

		return map;
	}
}

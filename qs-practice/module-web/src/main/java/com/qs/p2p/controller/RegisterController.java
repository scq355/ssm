package com.qs.p2p.controller;

import com.google.common.base.Strings;
import com.qs.p2p.constant.Constants;
import com.qs.p2p.model.User;
import com.qs.p2p.service.UserService;
import com.qs.p2p.service.utils.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by scq on 2018-02-01 11:53:05
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String show() {
		return "/login/register";
	}

	/**
	 * 注册
	 * @param email:邮箱
	 * @param userName:用户名
	 * @param password:密码
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Result<Map<String, Object>> register(String email, String userName,
												String password, String verifyCode,
												HttpServletRequest request) {
		if (Strings.isNullOrEmpty(email)) {
			return new Result<>(100, "邮箱不能为空");
		}
		if (Strings.isNullOrEmpty(userName)) {
			return new Result<>(101, "用户名不能为空");
		}
		if (Strings.isNullOrEmpty(password)) {
			return new Result<>(102, "密码不能为空");
		}
		String regex = "^[a-zA-Z]+$";
		if(password.matches(regex)) {
			return new Result<>(103, "密码不能为纯字母");
		}
		if (!isVerifyCorrect(verifyCode, request)) {
			return new Result<>(104, "验证码不能为空");
		}

		User userVO = new User();
		userVO.setUserName(userName);
		User userExist = userService.get(userVO);
		if (userExist != null) {
			return new Result<>(105, "用户名已经存在");
		}

		User user = new User();
		user.setUserName(userName);
		user.setNickName(userName);
		user.setPhoneNumber("18710755118");
		user.setPassword(DigestUtils.md5Hex(DigestUtils.sha1Hex(password.trim()) + Constants.PASSWORD_KEY));
		user.setEmail(email);
		user.setRegisterTime(new Date());
		try {
			userService.insert(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new Result<>(-1, e.getMessage());
		}

		return new Result<>(0, "注册成功");
	}
}

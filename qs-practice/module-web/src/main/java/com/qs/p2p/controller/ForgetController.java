package com.qs.p2p.controller;

import com.qs.p2p.service.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @description : 忘记密码
 * Created by scq on 2018-02-01 13:12:36
 */
@Controller
@RequestMapping(value = "/forget")
public class ForgetController {

	@RequestMapping(method = RequestMethod.GET)
	public String show() {
		return "/login/forget-password";
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public Result<Map<String, String>> reset() {
		return new Result<>();
	}
}

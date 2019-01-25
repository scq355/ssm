package com.qs.p2p.controller;

import com.qs.p2p.constant.Constants;
import com.qs.p2p.web.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by scq on 2018-02-01 15:14:06
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpServletResponse response) {
		CookieUtils.removeCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME);
		return "redirect:/login/";
	}
}

package com.qs.p2p.controller;

import com.qs.p2p.constant.Constants;
import com.qs.p2p.utils.BlowfishUtils;
import com.qs.p2p.utils.Config;
import com.qs.p2p.utils.UrlUtils;
import com.qs.p2p.web.CookieUtils;
import com.qs.p2p.web.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by scq on 2018-01-17 17:34:26
 */
public abstract class BaseController {
	@ModelAttribute("baseDomain")
	public String getBaseDomain() {
		return Constants.BASE_DOMAIN;
	}

	@ModelAttribute("staticDomain")
	public String getStaticDomain() {
		return Constants.STATIC_DOMAIN;
	}

	@ModelAttribute("homeDomain")
	public String getHomeDomain() {
		return Constants.HOME_DOMAIN;
	}

	@ModelAttribute("returnUrl")
	public String getReturnUrl() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return RequestUtils.getRequestURIWithQueryString(request);
	}

	@ModelAttribute("encodedReturnUrl")
	public String getEncodedReturnUrl() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return UrlUtils.encode(RequestUtils.getRequestURIWithQueryString(request));
	}

	/**
	 * 校验结果是否正确
	 */
	protected boolean isVerifyCorrect(String verify, HttpServletRequest request) {
		Cookie cookie = CookieUtils.getCookie(request, Constants.VALIDATE_CODE);
		String newValidateCode = BlowfishUtils.encrypt(verify.toUpperCase(), Constants.COOKIE_NAME);
		return newValidateCode.equals(cookie.getValue());
	}

	/**
	 * 移除cookie
	 */
	protected void removeCookie(HttpServletResponse response) {
		CookieUtils.removeCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME);
	}
}

package com.qs.p2p.filter;

import com.google.common.base.Strings;
import com.qs.p2p.constant.Constants;
import com.qs.p2p.utils.Config;
import com.qs.p2p.utils.UrlUtils;
import com.qs.p2p.utils.UserIdentity;
import com.qs.p2p.utils.UserIdentityUtils;
import com.qs.p2p.web.CookieUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by scq on 2018-01-17 09:52:44
 */
public class PassportFilter implements Filter {
	private boolean loginRequiredDefault = true;
	private List<Pattern> loginRequiredUrlPatterns = new LinkedList<>();
	private List<Pattern> loginIgnoredUrlPatterns = new LinkedList<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loadConfig();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		boolean loginRequired = isLoginRequired(request);
		boolean login = isLogin(request);

		if (loginRequired) {
			if (login) {
				if (isSessionExpired(request)) {
					redirectToLogin(request, response);
					return;
				} else {
					updateLastVisitTime(request, response);
				}
			} else {
				redirectToLogin(request, response);
				return;
			}
		} else {
			if (login) {
				if (isSessionExpired(request)) {
					removeCookie(request, response);
				} else {
					updateLastVisitTime(request, response);
				}
			}
		}

		filterChain.doFilter(request, response);
 	}

	@Override
	public void destroy() {}

	/**
	 * 加载配置
	 */
	private void loadConfig() {
		loginRequiredDefault = !"false".equals(Config.getConfig().get("login.required.default"));

		String loginRequiredUrlRegex = Config.getConfig().get("login.required.url.regex");
		if (!Strings.isNullOrEmpty(loginRequiredUrlRegex)) {
			String[] regexes = loginRequiredUrlRegex.split(",");
			for (String regex : regexes) {
				loginIgnoredUrlPatterns.add(Pattern.compile(regex.trim()));
			}
		}

		String loginIgnoredUrlRegex = Config.getConfig().get("login.ignored.url.regex");
		if (!Strings.isNullOrEmpty(loginIgnoredUrlRegex)) {
			String[] regexes = loginIgnoredUrlRegex.split(",");
			for (String regex : regexes) {
				loginIgnoredUrlPatterns.add(Pattern.compile(regex.trim()));
			}
		}
	}

	/**
	 * 是否需要登录
	 */
	private boolean isLoginRequired(HttpServletRequest request) {
		String uri = request.getRequestURI();
		for (Pattern pattern : loginRequiredUrlPatterns) {
			if (pattern.matcher(uri).matches()) {
				return true;
			}
		}
		for (Pattern pattern : loginIgnoredUrlPatterns) {
			if (pattern.matcher(uri).matches()) {
				return false;
			}
		}
		return loginRequiredDefault;
	}

	/**
	 * 是否已经登录
	 */
	private boolean isLogin(HttpServletRequest request) {
		Cookie cookie = CookieUtils.getCookie(request, Constants.COOKIE_NAME);
		if (cookie == null) {
			return false;
		}

		request.setAttribute(Constants.USER_IDENTITY_KEY, UserIdentityUtils.unserialize(cookie.getValue()));
		return true;
	}

	/**
	 * session是否过期
	 */
	private boolean isSessionExpired(HttpServletRequest request) {
		UserIdentity userIdentity = (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
		if (userIdentity != null) {
			Date expiryTime = DateUtils.addMinutes(userIdentity.getLastVisitTime(), Constants.SESSION_TIMEOUT);
			return expiryTime.before(new Date());
		}
		return false;
	}

	/**
	 * 请求跳转到登录页
	 */
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURI());
		String queryString = request.getQueryString();
		if (queryString != null) {
			url.append("?");
			url.append(queryString);
		}

		String loginUrl = Config.getConfig().get("login.url");

		StringBuilder redirectUrl = new StringBuilder();
		redirectUrl.append(loginUrl);
		redirectUrl.append("?returnUrl=");
		redirectUrl.append(UrlUtils.encode(url.toString()));

		try {
			response.sendRedirect(redirectUrl.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新上次访问时间
	 */
	private void updateLastVisitTime(HttpServletRequest request, HttpServletResponse response) {
		UserIdentity userIdentity = (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
		if (userIdentity != null) {
			userIdentity.setLastVisitTime(new Date());
			CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME, UserIdentityUtils.serialize(userIdentity));
		}
	}

	/**
	 * 删除cookie
	 */
	private void removeCookie(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.USER_IDENTITY_KEY, null);
		CookieUtils.removeCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME);
	}
}

package com.sangame.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by scq on 2018-01-09 10:20:15
 */
public final class CookieUtils {
	public static Cookie getCookie(String name) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return getCookie(request, name);
	}

	public static void addCookie(String domain, String path, String name, String value) {
		addCookie(domain, path, name, value, -1);
	}

	public static void addCookie(String domain, String path, String name, String value, int maxAge) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		addCookie(response, domain, path, name, value, maxAge);
	}

	public static void removeCookie(String domain, String path, String name) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		removeCookie(response, domain, path, name);
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static void addCookie(HttpServletResponse response, String domain, String path, String name, String value) {
		addCookie(response, domain, path, name, value, -1);
	}

	public static void addCookie(HttpServletResponse response, String domain, String path, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletResponse response, String domain, String path, String name) {
		addCookie(response, domain, path, name, null, 0);
	}

	private CookieUtils() {
	}
}

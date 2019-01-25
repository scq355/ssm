package com.qs.p2p.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Created by scq on 2018-02-07 09:50:07
 */
public class SessionFilter implements Filter{

	public FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	public static boolean isContains(String container, String[] regex) {
		boolean result = false;

		for (int i = 0; i < regex.length; i++) {
			if (container.indexOf(regex[i]) != -1) {
				return true;
			}
		}
		return result;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);

		// 登录登陆页面
		String loginStrings = config.getInitParameter("loginStrings");
		// 过滤资源后缀参数
		String includeStrings = config.getInitParameter("includeStrings");
		// 没有登陆转向页面
		String redirectPath = config.getInitParameter("redirectPath");
		// 过滤器是否有效
		String disableFilter = config.getInitParameter("disableFilter");

		if (disableFilter.toUpperCase().equals("Y")) {
			filterChain.doFilter(request, servletResponse);
			return;
		}

		String[] loginList = loginStrings.split(",");
		String[] includeList = includeStrings.split(",");

		if (this.isContains(request.getRequestURI(), includeList)) { // 只对指定过滤参数后缀进行过滤
			filterChain.doFilter(request, servletResponse);
			return;
		}

		if (this.isContains(request.getRequestURI(), loginList)) { // 对登录页面不进行过滤
			filterChain.doFilter(request, servletResponse);
			return;
		}

		String user = ( String ) request.getSession().getAttribute("_admin_token");//判断用户是否登录
		if (user == null) {
			wrapper.sendRedirect(redirectPath);
			return;
		}else {
			filterChain.doFilter(request, servletResponse);
			return;
		}
	}

	@Override
	public void destroy() {
		this.config = null;
	}
}

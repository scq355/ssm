package com.qs.p2p.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by scq on 2018-02-07 09:21:36
 */
public class MyFilter implements Filter {

	public FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig.getFilterName();
		filterConfig.getServletContext();
		filterConfig.getInitParameterNames();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

	}

	@Override
	public void destroy() {

	}
}

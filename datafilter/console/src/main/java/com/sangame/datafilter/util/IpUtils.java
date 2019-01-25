package com.sangame.datafilter.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
* @Description: IP相关的通用处理
* @author yeqingfeng
* @date 2017年5月8日        
*/
public class IpUtils {
	
	protected final static Logger LOG = LoggerFactory.getLogger(IpUtils.class);
	
	/**
	 * 获取客户端的真实ip
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (LOG.isDebugEnabled()) {
			LOG.debug("x-forwarded-for = {}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (LOG.isDebugEnabled()) {
				LOG.debug("Proxy-Client-IP = {}", ip);
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			if (LOG.isDebugEnabled()) {
				LOG.debug("WL-Proxy-Client-IP = {}", ip);
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (LOG.isDebugEnabled()) {
				LOG.debug("RemoteAddr-IP = {}", ip);
			}
		}
		
		String[] ipArray = ip.split(",");
		for (String s : ipArray) {// 过滤掉局域网ip
			s = s.trim();
			if (!(s.startsWith("10.") || s.startsWith("192."))) {
				return s;
			}
		}
		
		if (StringUtils.isNotEmpty(ip)) {
			ip = ip.split(",")[0];
		}
		return ip;
	}
}

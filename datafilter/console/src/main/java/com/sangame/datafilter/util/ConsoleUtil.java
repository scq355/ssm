package com.sangame.datafilter.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.sangame.datafilter.dto.AdminDto;

/**   
* @Description: datafilter-console的一些通用方法
* @author yeqingfeng
* @date 2017年4月28日        
*/
public class ConsoleUtil {
	
	/**
	 * 获取当前登录的管理员
	 * @return
	 */
	public static AdminDto getCurrentAdmin() {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject == null) return null;
			Session session = subject.getSession(false);  
		    if (session != null) {
		    	AdminDto admin =  (AdminDto) session.getAttribute("userInfo");
				if (admin != null) {
					return admin;
				}
			}
		} catch(Exception e){
			
		}
	    return null;
	}
	
	/**
	 * 获取当前管理员的名称
	 * @return
	 */
	public static String getCurAdminName() {
		AdminDto admin = getCurrentAdmin();
		if (admin != null) {
			return admin.getRealName();
		}
		return "未知用户";
	}
	
	
	/**
	 * 判断已有管理员登录
	 * @return
	 */
	public static boolean isLogin() {
		if(getCurrentAdmin()!=null){
			return true;
		}
		return false;
	}
}

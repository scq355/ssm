package com.sangame.datafilter.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.ModelAttribute;

import com.sangame.datafilter.common.persistence.model.FilterAdmin;

public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
       this.request = request;
       this.response = response;
       this.session = request.getSession();
   }	
	
	public static String getSavedRequestUrl() {  
	    /*Subject subject = SecurityUtils.getSubject();  
	    Session session = subject.getSession(false);  
	    if (session != null) {  
	    	SavedRequest savedRequest = (SavedRequest) session.getAttribute("shiroSavedRequest");
	        if(savedRequest!=null){
	        	return savedRequest.getRequestUrl();
	        }
	    } */
	    return "";
	}  

	public FilterAdmin getCurrentUser() {
		/*Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(false);  
	    if (session != null) {
	    	FilterAdmin user =  (FilterAdmin) session.getAttribute("userInfo");
			if (user != null) {
				return user;
			}
		}*/
	    return null;
	}
	
	public Long getCurrentUserId() {
		FilterAdmin user = getCurrentUser();
		if(user != null){
			return user.getId();
		}
		return null;
	}
	
	public boolean isLogin() {
		if(getCurrentUser()!=null){
			return true;
		}
		return false;
	}
	
}

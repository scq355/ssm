package com.sangame.datafilter.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sangame.datafilter.common.persistence.model.FilterAdmin;
import com.sangame.datafilter.constant.ConsoleConstant.SystemConfigKey;
import com.sangame.datafilter.constant.ConsoleConstant.deleteFlagType;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.dto.AdminDto;
import com.sangame.datafilter.service.FilterAdminService;
import com.sangame.datafilter.service.SysConfigService;
import com.sangame.datafilter.util.ConsoleUtil;
import com.sangame.datafilter.util.IpUtils;
import com.sangame.datafilter.util.Render;

@Controller
@RequestMapping(value="/admin", method=RequestMethod.GET)
public class AdminController {

	private final static Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private FilterAdminService adminService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(){
		return PageConstant.LOGIN_PAGE;
	}
	
	@ResponseBody
	@RequestMapping(value = "/openSnCheck.do", method=RequestMethod.GET)
	public Render openSnCheck() {
		//判断系统配置项里是否开启了谷歌身份认证  0为禁用，1为启用
		String configValue = sysConfigService.getConfigValueByKey(SystemConfigKey.CHECK_AUTH_CODE.getValue());
		return new Render(true, "请求数据成功！请求配置项：是否启用谷歌身份认证  0为禁用  1为启用", configValue);
	}
	
	/**
	 * 管理员登录
	 * @param request
	 * @param adminName
	 * @param password
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value="doLogin.do", method=RequestMethod.POST)
//	public Render doLogin(HttpServletRequest request,
//			@RequestParam(value = "adminName", required = true) String adminName,
//			@RequestParam(value = "password", required = true) String password) {
//		FilterAdmin user = adminService.findAdminByName(adminName);
//		if (user == null) {
//			return new Render(false, "登录失败，用户名不存在");
//		}
//		if (deleteFlagType.TRUE.getValue().equals(user.getDeleteFlag())) {
//			log.info(user.getDeleteFlag() + "===============" + deleteFlagType.TRUE.getValue());
//			return new Render(false, "登录失败，该用户被禁用");
//		}
//
//		AdminDto ldapDto = adminService.getUserByUid(adminName);
//		if (ldapDto == null) {
//			return new Render(false, "获取用户信息出错，请联系管理员");
//		}
//		//判断系统配置项里是否开启了谷歌身份认证  0为禁用，1为启用
//		String configValue = sysConfigService.getConfigValueByKey(SystemConfigKey.CHECK_AUTH_CODE.getValue());
//		if(StringUtils.isNotBlank(configValue) && configValue.equals("1")){
//			//验证动态口令卡
//			if (!adminService.checkAuthCode(ldapDto.getAuthKey(), ldapDto.getSnPassword())) {
//				user.setFailNumber(user.getFailNumber() + 1);
//				adminService.update(user);
//				return new Render(false, "授权码错误");
//			}
//		}
//
//		// 验证密码
//		if (!adminService.authenticate(adminName, password)) {
//			user.setFailNumber(user.getFailNumber() + 1);
//			adminService.update(user);
//			return new Render(false, "用户名或密码错误");
//		}
//		String ip = IpUtils.getClientIp(request);
//		ldapDto.setLoginIp(ip);
//		//loadLoginInfo(user, ldapDto);
//
//		user.setLastLoginTime(new Date());
//		user.setLastLoginIp(ip);
//		user.setFailNumber(0);
//		adminService.update(user);
//
//        UsernamePasswordToken token = new UsernamePasswordToken(adminName, password);
//        token.setRememberMe(true);
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(token);
//        user.setPassword("");
//        subject.getSession().setAttribute("userInfo", ldapDto);
//        return new Render(true, "登录成功，正在加载数据...", ldapDto);
//	}
	
	@RequestMapping(value="doLogOut.do", method=RequestMethod.GET)
	public String doLogOut() {
		Subject currentUser = SecurityUtils.getSubject();       
        currentUser.logout();    
		return PageConstant.LOGIN_PAGE;
	}
	
}

package com.sangame.datafilter.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 用户登录后信息
 */
public class AdminDto implements Serializable {

	private static final long serialVersionUID = 4606881184035872721L;
	
	private Long id;
	private String name;
	private String realName;
	private String userPwd; // 密码
	private String mobile;
	private Long roleId;
	private String email;
	private Date lastLoginTime;
	private String lastLoginIp;
	private String memo;
	private Date loginTime;// 本次登录时间
	private String loginIp;// 本次登录IP
	private String authCode; // 图片验证码
	private int failNumber;
	private String operCode;
	private Long snPassword; // 动态口令卡
	private String authKey; // 动态口令密钥

	private Set<String> operCodeSet = new HashSet<String>();// 操作码
	private Set<String> menuCodeSet = new HashSet<String>();// 目录码

	public boolean hasOper(String operCode) {
		return operCodeSet.contains(operCode);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public int getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(int failNumber) {
		this.failNumber = failNumber;
	}

	public Long getGroudId() {
		return roleId;
	}

	public void setGroudId(Long groudId) {
		this.roleId = groudId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public Long getSnPassword() {
		return snPassword;
	}

	public void setSnPassword(Long snPassword) {
		this.snPassword = snPassword;
	}

	public Set<String> getOperCodeSet() {
		return operCodeSet;
	}

	public void setOperCodeSet(Set<String> operCodeSet) {
		this.operCodeSet = operCodeSet;
	}

	public Set<String> getMenuCodeSet() {
		return menuCodeSet;
	}

	public void setMenuCodeSet(Set<String> menuCodeSet) {
		this.menuCodeSet = menuCodeSet;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
}

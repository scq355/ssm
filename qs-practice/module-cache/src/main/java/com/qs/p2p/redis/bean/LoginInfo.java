package com.qs.p2p.redis.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Created by scq on 2018-01-25 15:06:43
 */
@RedisHash(value = "loginInfos")
public class LoginInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;
	private String password;
	private String loginIP;
	private String loginAddress;
	private String loginTime;

	public LoginInfo() {}

	public LoginInfo(String userName, String password, String loginIP, String loginAddress, String loginTime) {
		this.userName = userName;
		this.password = password;
		this.loginIP = loginIP;
		this.loginAddress = loginAddress;
		this.loginTime = loginTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLoginAddress() {
		return loginAddress;
	}

	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "LoginInfo{" +
				"userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", loginIP='" + loginIP + '\'' +
				", loginAddress='" + loginAddress + '\'' +
				", loginTime='" + loginTime + '\'' +
				'}';
	}
}

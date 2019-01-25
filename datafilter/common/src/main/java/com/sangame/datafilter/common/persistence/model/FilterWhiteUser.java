package com.sangame.datafilter.common.persistence.model;


import java.io.Serializable;
/**
 * 
* @Description: 白名单表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterWhiteUser extends BaseModel implements Serializable {

	private static final long serialVersionUID = -4623154824614179363L;

	private Long userId;			
	private String userName;
	private String ip;
	private Integer whiteType;			//白名单类型    1：用户ID白名单   2：IP白名单
	private String whiteReason;			//白名单添加理由
	private Integer deleteFlag;

	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWhiteReason() {
		return whiteReason;
	}
	public void setWhiteReason(String whiteReason) {
		this.whiteReason = whiteReason;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getWhiteType() {
		return whiteType;
	}

	public void setWhiteType(Integer whiteType) {
		this.whiteType = whiteType;
	}

	@Override
	public String toString() {
		return "FilterWhiteUser{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", ip='" + ip + '\'' +
				", type=" + whiteType +
				", whiteReason='" + whiteReason + '\'' +
				", deleteFlag=" + deleteFlag +
				'}';
	}
}
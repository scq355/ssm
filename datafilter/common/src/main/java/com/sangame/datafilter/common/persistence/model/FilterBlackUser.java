package com.sangame.datafilter.common.persistence.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterBlackUser extends BaseModel {

	private static final long serialVersionUID = -7004240349615671048L;

	private Long userId;
	private String userName;
	private String ip;
	private Integer freezeTime;		//非数据库字段，冻结时间区段
	private Date blackEndTime;		//冻结截至时间
	private String blackReason;		//冻结理由
	private Integer blackState;		//冻结状态  1：冻结  2：解冻
	private Integer blackWay;		//冻结类型  1：账户冻结  2：IP冻结

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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getBlackEndTime() {
		return blackEndTime;
	}
	public void setBlackEndTime(Date blackEndTime) {
		this.blackEndTime = blackEndTime;
	}
	public String getBlackReason() {
		return blackReason;
	}
	public void setBlackReason(String blackReason) {
		this.blackReason = blackReason;
	}
	public Integer getBlackState() {
		return blackState;
	}
	public void setBlackState(Integer blackState) {
		this.blackState = blackState;
	}
	public Integer getBlackWay() {
		return blackWay;
	}
	public void setBlackWay(Integer blackWay) {
		this.blackWay = blackWay;
	}

	public Integer getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Integer freezeTime) {
		this.freezeTime = freezeTime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getBlackEndTimeStr() {
		if (blackEndTime == null) return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(blackEndTime);
	}
	
	@Override
	public String toString() {
		return "FilterBlackUser{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", ip='" + ip + '\'' +
				", freezeTime=" + freezeTime +
				", blackEndTime=" + blackEndTime +
				", blackReason='" + blackReason + '\'' +
				", blackState=" + blackState +
				", blackWay=" + blackWay +
				'}';
	}
}
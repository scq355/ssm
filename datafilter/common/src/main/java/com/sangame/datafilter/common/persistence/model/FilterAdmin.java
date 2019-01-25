package com.sangame.datafilter.common.persistence.model;

import java.util.Date;
/**
 * 
* @Description: 管理员表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterAdmin extends BaseModel {

	private static final long serialVersionUID = 8223002343200793335L;

	private String adminName;
	private String password;
	private Long roleId;			//角色ID
	private String lastLoginIp;		//最后登录ID
	private Date lastLoginTime;		//最后时间
	private Integer failNumber;		//连续登录失败次数
	private String remark;
	private Integer deleteFlag;

	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getFailNumber() {
		return failNumber;
	}
	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Override
	public String toString() {
		return "FilterAdmin [adminName=" + adminName + ", password=" + password + ", roleId=" + roleId
				+ ", lastLoginIp=" + lastLoginIp + ", lastLoginTime=" + lastLoginTime + ", failNumber=" + failNumber
				+ ", remark=" + remark + ", deleteFlag=" + deleteFlag + "]";
	}
	
	
}
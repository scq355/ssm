package com.sangame.datafilter.common.persistence.model;


public class FilterPermission extends BaseModel {

	private static final long serialVersionUID = 3115919114527290859L;

	private String permissionKey;		//权限关键字
	private String permissionName;		//权限名称
	private String sortCode;			//排序码

	public String getPermissionKey() {
		return permissionKey;
	}
	public void setPermissionKey(String permissionKey) {
		this.permissionKey = permissionKey;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
}
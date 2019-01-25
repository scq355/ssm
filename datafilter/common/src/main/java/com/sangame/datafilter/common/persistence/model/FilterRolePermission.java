package com.sangame.datafilter.common.persistence.model;

/**
 * 
* @Description: 角色权限关系表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterRolePermission extends BaseModel {

	private static final long serialVersionUID = 5677195528120731954L;

	private Long roleId;			//角色ID
	private Long permissionId;		//权限ID

	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}
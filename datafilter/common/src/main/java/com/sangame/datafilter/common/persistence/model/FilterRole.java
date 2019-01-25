package com.sangame.datafilter.common.persistence.model;

/**
 * 
* @Description: 角色表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterRole extends BaseModel {

	private static final long serialVersionUID = 7421501535564141627L;

	private String roleKey;		//角色关键字
	private String roleName;	//角色名称
	private String remark;		//备注

	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
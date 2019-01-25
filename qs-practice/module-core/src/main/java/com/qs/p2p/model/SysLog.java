package com.qs.p2p.model;

import com.qs.p2p.dao.AbstractPO;
import java.util.Date;

/**
 * 系统日志
 */
public class SysLog extends AbstractPO {
	private static final long serialVersionUID = 1L;

	private Integer id;
	//0 权限管理 1 系统设置 2 资金管理 3 用户管理 4 投资管理 5 推广运营 6 借款管理 7 内容管理
	private Integer moduleType;
	//权限名称
	private String name;
	//权限链接地址
	private String uri;
	//操作说明
	private String msg;
	private Integer adminId;
	private String ip;
	private Date createdAt;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(Integer id, boolean forceUpdate) {
		setId(id);
		if (forceUpdate) {
			addForceUpdateProperty("id");
		}
	}

	public Integer getId() {
		return id;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	public void setModuleType(Integer moduleType, boolean forceUpdate) {
		setModuleType(moduleType);
		if (forceUpdate) {
			addForceUpdateProperty("moduleType");
		}
	}

	public Integer getModuleType() {
		return moduleType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(String name, boolean forceUpdate) {
		setName(name);
		if (forceUpdate) {
			addForceUpdateProperty("name");
		}
	}

	public String getName() {
		return name;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setUri(String uri, boolean forceUpdate) {
		setUri(uri);
		if (forceUpdate) {
			addForceUpdateProperty("uri");
		}
	}

	public String getUri() {
		return uri;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setMsg(String msg, boolean forceUpdate) {
		setMsg(msg);
		if (forceUpdate) {
			addForceUpdateProperty("msg");
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public void setAdminId(Integer adminId, boolean forceUpdate) {
		setAdminId(adminId);
		if (forceUpdate) {
			addForceUpdateProperty("adminId");
		}
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setIp(String ip, boolean forceUpdate) {
		setIp(ip);
		if (forceUpdate) {
			addForceUpdateProperty("ip");
		}
	}

	public String getIp() {
		return ip;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setCreatedAt(Date createdAt, boolean forceUpdate) {
		setCreatedAt(createdAt);
		if (forceUpdate) {
			addForceUpdateProperty("createdAt");
		}
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}

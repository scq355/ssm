package com.qs.p2p.model;

import com.qs.p2p.dao.AbstractPO;

import java.util.Date;

/**
 * Created by scq on 2018-01-19 11:00:40
 */
public class TaskScheduler extends AbstractPO {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String moduleName;
	private String hostname;
	private String ipAddress;
	private Integer enabled;
	private Date createdAt;
	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(Integer id, boolean forceUpdate) {
		setId(id);

		if (forceUpdate) {
			addForceUpdateProperty("id");
		}
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setModuleName(String moduleName, boolean forceUpdate) {
		setModuleName(moduleName);

		if (forceUpdate) {
			addForceUpdateProperty("moduleName");
		}
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setHostname(String hostname, boolean forceUpdate) {
		setHostname(hostname);

		if (forceUpdate) {
			addForceUpdateProperty("hostname");
		}
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setIpAddress(String ipAddress, boolean forceUpdate) {
		setIpAddress(ipAddress);

		if (forceUpdate) {
			addForceUpdateProperty("ipAddress");
		}
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setEnabled(Integer enabled, boolean forceUpdate) {
		setEnabled(enabled);

		if (forceUpdate) {
			addForceUpdateProperty("enabled");
		}
	}

	public Date getCreatedAt() {
		return createdAt;
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUpdatedAt(Date updatedAt, boolean forceUpdate) {
		setUpdatedAt(updatedAt);

		if (forceUpdate) {
			addForceUpdateProperty("updatedAt");
		}
	}

	@Override
	public String toString() {
		return "TaskScheduler{" +
				"id=" + id +
				", moduleName='" + moduleName + '\'' +
				", hostname='" + hostname + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", enabled=" + enabled +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}

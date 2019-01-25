package com.sangame.datafilter.common.persistence.model;

import java.io.Serializable;

/**
 * 系统版本表
 */
public class SysVersion implements Serializable{

	private static final long serialVersionUID = -8057726970400418749L;

	private String sysVersion;

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}

}

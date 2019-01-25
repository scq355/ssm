package com.sangame.datafilter.config;

import org.w3c.dom.Node;

import com.sangame.datafilter.util.BaseConfig;

/**
 * 配置文件BEAN 对应 config.xml
 */
public class CommonConfig extends BaseConfig {

	private String version; // 版本号
	private String buildNo; // 构建号
	
	private String whiteIp;
	private boolean openIpFilter;

	public CommonConfig(String configFilePath) {
		super(configFilePath);
		load();
	}

	private void load() {
		setVersion(super.getValue("product.version"));
		setBuildNo(super.getValue("build.number"));
		
		setOpenIpFilter(super.getBooleanValue("open.filter.ip"));
		setWhiteIp(super.getValue("access.filter.ip"));
	}

	@Override
	protected void loadSpecial(Node n) {
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuildNo() {
		return buildNo;
	}

	public void setBuildNo(String buildNo) {
		this.buildNo = buildNo;
	}
	
	public String getWhiteIp() {
		return whiteIp;
	}

	public void setWhiteIp(String whiteIp) {
		this.whiteIp = whiteIp;
	}

	public boolean isOpenIpFilter() {
		return openIpFilter;
	}

	public void setOpenIpFilter(boolean openIpFilter) {
		this.openIpFilter = openIpFilter;
	}
}

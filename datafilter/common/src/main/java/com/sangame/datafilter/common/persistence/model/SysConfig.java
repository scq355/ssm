package com.sangame.datafilter.common.persistence.model;

/**
 * 
* @Description: 系统配置表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class SysConfig extends BaseModel {

	private static final long serialVersionUID = 86910628870960662L;

	private String configKey;			//配置关键字
	private String showName;			//配置名称
	private String configValue;			//配置值
	private String remark;				//备注

	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysConfig{" +
				"configKey='" + configKey + '\'' +
				", showName='" + showName + '\'' +
				", configValue='" + configValue + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
package com.sangame.datafilter.common.persistence.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @Description: 来源表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterSource extends BaseModel {

	private static final long serialVersionUID = 3692416253907965697L;

	private String sourceKey;		//来源关键字
	private String sourceName;		//来源名称
	private Integer auditRule;		//审核方式  1：先发后审  2：先审后发  3：动态审核
	private List<FilterSourceTimeset> timesetList = new ArrayList<FilterSourceTimeset>();
	
	public String getSourceKey() {
		return sourceKey;
	}
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getAuditRule() {
		return auditRule;
	}
	public void setAuditRule(Integer auditRule) {
		this.auditRule = auditRule;
	}
	public List<FilterSourceTimeset> getTimesetList() {
		return timesetList;
	}
	public void setTimesetList(List<FilterSourceTimeset> timesetList) {
		this.timesetList = timesetList;
	}
	
}
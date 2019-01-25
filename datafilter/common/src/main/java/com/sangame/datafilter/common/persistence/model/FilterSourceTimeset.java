package com.sangame.datafilter.common.persistence.model;

/**
 * 
* @Description: 来源时间设置表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterSourceTimeset extends BaseModel {

	private static final long serialVersionUID = -831983347580938555L;

	private Long sourceId;			//来源ID
	private Integer startHour;		//开始时间，时间区间内为先审后发，区间外为先发后审
	private Integer endHour;		//结束时间

	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}
	public Integer getEndHour() {
		return endHour;
	}
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
}
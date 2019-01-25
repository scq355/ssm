package com.sangame.datafilter.common.persistence.model.audit;


import java.io.Serializable;

public class FilterDataException extends BaseUtilEntity implements Serializable{

	private static final long serialVersionUID = -2245290050265401479L;

	private String msg;
	private Integer projectType;
	private Integer status;
	private Integer dealType;
	private String json;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
}
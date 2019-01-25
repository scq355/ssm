package com.sangame.datafilter.common.persistence.model.audit;


public class FilterInvestmentAudit extends BaseAuditModel {

	private Integer dataType;

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
}
package com.sangame.datafilter.common.persistence.model.audit;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterAuditLog implements Serializable ,Cloneable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer operation;
	private String reason;
	private Integer type;
	private Long auditId;
	private String creator;
	private Date createdAt;

	@Override
	public Object clone() throws CloneNotSupportedException {
		FilterAuditLog filterAuditLog = (FilterAuditLog) super.clone();
		return filterAuditLog;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getAuditId() {
		return auditId;
	}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreateTime() {
		if (createdAt == null) return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(createdAt);
	}
}
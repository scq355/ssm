package com.sangame.datafilter.common.persistence.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = 7189852734146826929L;
	
	private Long id;
	private String creator;
	private String modifier;
	private Date createdAt;
	private Date updatedAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getCreateTime() {
		if (createdAt == null) return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(createdAt);
	}
	
	public String getUpdateTime() {
		if (updatedAt == null) return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(updatedAt);
	}
	
	
}
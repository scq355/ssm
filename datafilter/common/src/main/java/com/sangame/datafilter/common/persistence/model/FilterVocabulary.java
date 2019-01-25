package com.sangame.datafilter.common.persistence.model;

/**
 * 
* @Description: 过滤词表
* @author yeqingfeng
* @date 2017年5月15日
 */
public class FilterVocabulary extends BaseModel {

	private static final long serialVersionUID = -3810465605797879902L;

	private String content;				//过滤词内容
	private Integer type;				//过滤词类型  1：敏感词  2：非法词
	private Integer deleteFlag;			//删除标志

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
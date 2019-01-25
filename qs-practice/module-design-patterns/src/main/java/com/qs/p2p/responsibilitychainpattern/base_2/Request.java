package com.qs.p2p.responsibilitychainpattern.base_2;

/**
 * Created by scq on 2018-03-24 14:31:59
 */
public class Request {
	private String requestType;
	private String requestContent;
	private Integer number;

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}

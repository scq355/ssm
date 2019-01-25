package com.sangame.datafilter.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果类
 *
 */
public class Render {

	public final static int SUCCESS = 0;

	public final static int FAILURE = -1;

	private boolean flag; 				// 是否执行成功
	private boolean timeout; 			// 是否超时
	private int code = SUCCESS; 		// 返回代码
	private String msg; 				// 错误消息
	private Object data; 					// 返回数据

	public Render() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public Render(boolean flag, String msg, Integer totalRecords, List dataList) {
		super();
		this.flag = flag;
		this.timeout = false;
		this.msg = msg;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iTotalRecords", totalRecords);
		map.put("iTotalDisplayRecords", totalRecords);
		map.put("aaData", dataList);
		this.data = map;
	}

	public Render(boolean timeout, boolean flag, int code, String msg, Object data) {
		super();
		this.timeout = timeout;
		this.flag = flag;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Render(boolean flag, int code, String msg, Object data) {
		super();
		this.flag = flag;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Render(boolean timeout, boolean flag, String msg, Object data) {
		super();
		this.timeout = timeout;
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}

	public Render(boolean flag, String msg, Object data) {
		super();
		this.timeout = false;
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}

	public Render(boolean flag, String msg) {
		super();
		this.timeout = false;
		this.flag = flag;
		this.msg = msg;
	}

	public boolean isTimeout() {
		return timeout;
	}

	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}


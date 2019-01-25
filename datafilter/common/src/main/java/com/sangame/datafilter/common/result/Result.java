package com.sangame.datafilter.common.result;

public class Result {
	
	final public static Result SUCCESS = new Result(1, "success");
	final public static Result ERROR = new Result(0, "error");
	
	private int code;
	private String msg;
	private Object context;

	public Result() {
	}
	
	public Result(int code, String msg) {
		this(code, msg, null);
	}

	public Result(int code, String msg, Object context) {
		this.code = code;
		this.msg = msg;
		this.context = context;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Result copyThis() {
		return new Result(code, msg, null);
	}

	public static Result error(String error) {
		return new Result(ERROR.code, error);
	}
	
	public static Result success(Object ctx) {
		Result r = Result.SUCCESS.copyThis();
		r.setContext(ctx);
		return r;
	}
}

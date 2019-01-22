package com.sangame.common;

/**
 * Created by nudtn on 2017/4/30.
 */
public class Result {

    final public static Result SUCCESS = new Result(1, "success");
    final public static Result ERROR = new Result(0, "error");

    private int code;
    private String message;
    private Object context;

    public Result(int code, String message) {
        this(code, message, null);
    }
    public Result(int code, String message, Object context) {
        this.code = code;
        this.message = message;
        this.context = context;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }
    public Result copyThis() {
        return new Result(code, message, null);
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

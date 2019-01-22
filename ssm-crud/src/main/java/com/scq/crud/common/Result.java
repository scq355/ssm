package com.scq.crud.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nudtn on 2017/8/8.
 */
public class Result {

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(500);
        result.setMsg("fail");
        return result;
    }

    public Result add(String key, Object value) {
        this.getContext().put(key, value);
        return this;
    }

    private int code;
    private String msg;

    private Map<String, Object> context = new HashMap<>();

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

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }
}

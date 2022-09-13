package com.processes.ag_bpmn_v1.util;

public class Result {

    int code;
    String msg;
    Object data;

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

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result err(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

}

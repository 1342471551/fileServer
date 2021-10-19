package com.transportocol.transfor.common;

/**
 * @author wangyj
 * @Description: (统一API结果处理类)
 * @date 2020/12/13 13:12
 */
public class Result<T> {

    private int code;
    private String message;
    private T t;

    public Result(int code, String message, T t) {
        this.code = code;
        this.message = message;
        this.t = t;
    }

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code.code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

package com.cly.scanlibrary.internet;

/**
 * 网络请求基类
 * Created by 丛龙宇 on 2017/8/17.
 */

public class HttpResult<T> {

    private int code;
    private String msg;
    private T content;

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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

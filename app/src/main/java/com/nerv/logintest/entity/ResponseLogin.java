package com.nerv.logintest.entity;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ResponseLogin {

    /**
     * status : 1
     * message : 密码不正确, 您还可以重试4次
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

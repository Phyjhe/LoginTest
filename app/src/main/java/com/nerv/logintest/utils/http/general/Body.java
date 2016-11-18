package com.nerv.logintest.utils.http.general;

/**
 * Created by Administrator on 2016/10/18.
 */
public abstract class Body {
    public static final String formContentType = "application/x-www-form-urlencoded";
    public static final String jsonContentType = "application/json; Charset=utf-8";
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public Body(String contentType) {

        this.contentType = contentType;
    }
    public abstract byte[] getContentByByte();
}

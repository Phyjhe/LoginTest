package com.nerv.logintest.utils.http.general;

/**
 * Created by Administrator on 2016/10/19.
 */

public class JsonBody extends Body {
    private String body;
    public JsonBody(String contentType,String body) {
        super(contentType);
        this.body=body;
    }

    @Override
    public byte[] getContentByByte() {
        return body.getBytes();
    }


}

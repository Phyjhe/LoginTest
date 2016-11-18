package com.nerv.logintest.utils.http.general;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Request {
    private String method;
    private String url;
    private Headers header;
    private Body body;
    private Request(Builder builder) {
        this.url = builder.url;
        this.method=builder.method;
        this.body =builder.body;
        this.header=builder.builderOfHeaders.build();
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Headers getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    public static class Builder{
        private String url;
        private String method="GET";
        private Body body;
        private Headers.Builder builderOfHeaders;

        public Builder (){
            builderOfHeaders=new Headers.Builder();
        }
        public Builder addHeader(String name,String value){
            builderOfHeaders.addHeader(name,value);
            return this;
        }
        public Builder post(Body body){
            method="POST";
            this.body=body;
            addHeader("Content-type",body.getContentType());
            return this;
        }
        public Builder setURL(String url){
            this.url=url;
            return this;
        }
        public Request build(){
            return new Request(this);
        }

    }
}

package com.nerv.logintest.utils.http.general;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Headers {
    private List<String> namesAndValues;

    public Headers(Builder builder) {
        this.namesAndValues = builder.namesAndValues;
    }

    public List<String> getNamesAndHeaders() {
        return namesAndValues;
    }

    public static class Builder{
        private List<String> namesAndValues;
        public Builder() {
            namesAndValues =new ArrayList<String>();
        }
        public Builder addHeader(String name,String value){
            namesAndValues.add(name);
            namesAndValues.add(value);
            return this;
        }
        public Builder addHeaders(Headers headers){
            namesAndValues.addAll(headers.namesAndValues);
            return this;
        }
        public Headers build(){
            return new Headers(this);
        }
    }
}

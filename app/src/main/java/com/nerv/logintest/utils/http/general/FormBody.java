package com.nerv.logintest.utils.http.general;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */

public class FormBody extends Body {
    private List<String> enCodeValues;
    private List<String> enCodeNames;

    public FormBody(Builder builder) {
        super(formContentType);
        this.enCodeValues = builder.values;
        this.enCodeNames = builder.names;
    }

    public List<String> getEnCodeNames() {
        return enCodeNames;
    }

    public List<String> getEnCodeValues() {
        return enCodeValues;
    }

    @Override
    public byte[] getContentByByte() {
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < enCodeNames.size(); i++) {
            sb.append(enCodeNames.get(i));
            sb.append("=");
            sb.append(enCodeValues.get(i));
            sb.append("&");
        }
        if (sb.length()!=0) sb.deleteCharAt(sb.length()-1);
        return sb.toString().getBytes();
    }


    public static class Builder {
        private List<String> names= new ArrayList<String>();
        private List<String> values= new ArrayList<String>();
        public Builder add(String name ,String value){
            names.add(name);
            values.add(value);
            return this;
        }
        public Builder addEncode(String name ,String value){
            try {
                names.add(URLEncoder.encode(name,"utf-8"));
                values.add(URLEncoder.encode(value,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return this;
        }
        public Body build(){
            return new FormBody(this);
        }
    }
}

package com.nerv.logintest.mvp.modle.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.modle.SetPwdModle;
import com.nerv.logintest.utils.APIUtils;
import com.nerv.logintest.utils.http.general.Body;
import com.nerv.logintest.utils.http.general.FormBody;
import com.nerv.logintest.utils.http.general.HttpUtils;
import com.nerv.logintest.utils.http.general.Request;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class SetPwdImpl implements SetPwdModle {
    @Override
    public void setPwd(String numbers, final SetCallBack setCallBack) {
        String[] str=numbers.split("_");
        if (str.length<3)return;
        HashMap<String,String> map=new HashMap<>();
        map.put("mobile",str[0]);
        map.put("password",str[2]);
        map.put("verify_code",str[1]);
        Body body=new FormBody.Builder()
                .add("mobile",str[0])
                .add("password",str[2])
                .add("verify_code",str[1])
                .build();
        Request.Builder builder=new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .setURL("https://passport.4c.cn/api/v1//signup?")
                .post(body);
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.CallBack() {
            @Override
            public void onResponse(String response) {
                Log.d("sethahaha", "hahaha"+response);
                ResponseCheckNum responseCheckNum= JSON.parseObject(response,ResponseCheckNum.class);
                if (responseCheckNum.getStatus()==0){
                    setCallBack.setSuccess();
                    return;
                }
                setCallBack.SetFaild(responseCheckNum);
            }

            @Override
            public void onError() {

            }
        });
    }
}

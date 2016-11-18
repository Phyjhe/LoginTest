package com.nerv.logintest.mvp.modle.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.modle.CheckModle;
import com.nerv.logintest.utils.APIUtils;
import com.nerv.logintest.utils.http.general.Body;
import com.nerv.logintest.utils.http.general.FormBody;
import com.nerv.logintest.utils.http.general.HttpUtils;
import com.nerv.logintest.utils.http.general.Request;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class CheckModleImpl implements CheckModle {

    @Override
    public void doCheck(final String numberAndCheckNum, final ChekCallBack chekCallBack) {
        String[] str=numberAndCheckNum.split("_");
        for (String s:str) {
            Log.d("CheckModleImpl", s);
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("type","verify");
        map.put("mobile",str[0]);
        map.put("verify_code",str[1]);
        Body body=new FormBody.Builder()
                .add("type","verify")
                .add("mobile",str[0])
                .add("verify_code",str[1])
                .build();
        Request.Builder builder=new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN",APIUtils.getXPassportApitoken(map))
                .post(body)
                .setURL("https://passport.4c.cn/api/v1/check_verify_code?");
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.CallBack() {
            @Override
            public void onResponse(String response) {
                Log.d("CheckModleImpl", response);
                ResponseCheckNum responseCheckNum= JSON.parseObject(response,ResponseCheckNum.class);
                if (responseCheckNum.isCorrect()){
                    chekCallBack.right(numberAndCheckNum);
                }else{
                    chekCallBack.erorr(responseCheckNum);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}

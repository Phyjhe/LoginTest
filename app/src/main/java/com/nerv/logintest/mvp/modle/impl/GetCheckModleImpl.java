package com.nerv.logintest.mvp.modle.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.modle.GetCheckModle;
import com.nerv.logintest.utils.APIUtils;
import com.nerv.logintest.utils.http.general.Body;
import com.nerv.logintest.utils.http.general.FormBody;
import com.nerv.logintest.utils.http.general.HttpUtils;
import com.nerv.logintest.utils.http.general.Request;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class GetCheckModleImpl implements GetCheckModle {
    @Override
    public void loadDatas(String number, final GetCheckCallBack getCheckCallBack) {
        HashMap<String,String> map=new HashMap<>();
        map.put("field","mobile");
        map.put("value",number);
        Body body=new FormBody.Builder()
                .add("value",number)
                .add("field","mobile")
                .build();
        Request.Builder builder=new Request.Builder()
                .setURL("https://passport.4c.cn/api/v1/exists?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.CallBack() {
            @Override
            public void onResponse(String response) {
                ResponseCheckNum responseCheckNum= JSON.parseObject(response,ResponseCheckNum.class);
                if (!responseCheckNum.isExists()){
                    getCheckCallBack.getSuccess(responseCheckNum);
                    return;
                }else{
                    getCheckCallBack.getFaild(responseCheckNum);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void loadNextPage(String number, final GetCheckCallBack getCheckCallBack) {
        HashMap<String,String> map=new HashMap<>();
        map.put("type","signup");
        map.put("mobile",number);
        Body body=new FormBody.Builder()
                .add("type","signup")
                .add("mobile",number)
                .build();
        Request.Builder builder=new Request.Builder()
                .setURL("https://passport.4c.cn/api/v1/sms?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.CallBack() {
            @Override
            public void onResponse(String response) {
                ResponseCheckNum responseCheckNum= JSON.parseObject(response,ResponseCheckNum.class);
                Log.d("GetCheckModleImpl", response);
                if (responseCheckNum.getStatus()==0){
                    getCheckCallBack.getSuccess(responseCheckNum);
                    return;
                }
                getCheckCallBack.getFaild(responseCheckNum);
            }

            @Override
            public void onError() {

            }
        });
    }
}

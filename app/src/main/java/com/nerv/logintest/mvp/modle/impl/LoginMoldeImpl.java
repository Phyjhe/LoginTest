package com.nerv.logintest.mvp.modle.impl;

import com.alibaba.fastjson.JSON;
import com.nerv.logintest.entity.ResponseLogin;
import com.nerv.logintest.mvp.modle.LoginModle;
import com.nerv.logintest.utils.APIUtils;
import com.nerv.logintest.utils.http.general.Body;
import com.nerv.logintest.utils.http.general.FormBody;
import com.nerv.logintest.utils.http.general.HttpUtils;
import com.nerv.logintest.utils.http.general.Request;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginMoldeImpl implements LoginModle {

    @Override
    public void loadDatas(String nameAndPwd, final LoginCallBack loginCallBack) {
        String[] str=nameAndPwd.split("_");
        if (str.length<2)return;
        HashMap<String,String>map=new HashMap<>();
        map.put("username",str[0]);
        map.put("password",str[1]);
        Body body=new FormBody.Builder()
                .add("username",str[0])
                .add("password",str[1])
                .build();
        Request.Builder builder=new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .setURL("https://passport.4c.cn/api/v1/login?")
                .post(body);

        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.CallBack() {
            @Override
            public void onResponse(String response) {
                ResponseLogin responseLogin= JSON.parseObject(response,ResponseLogin.class);
                if (responseLogin.getStatus()==1){
                    loginCallBack.loginFaild(responseLogin);
                }else if (responseLogin.getStatus()==0){
                    loginCallBack.loginSuccess();
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}

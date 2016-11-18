package com.nerv.logintest.mvp.modle;

import com.nerv.logintest.entity.ResponseLogin;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface LoginModle {
    void loadDatas(String nameAndPwd,LoginCallBack loginCallBack);
    interface LoginCallBack{
        void loginSuccess();
        void loginFaild(ResponseLogin responseLogin);
    }
}

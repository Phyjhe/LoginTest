package com.nerv.logintest.mvp.modle;

import com.nerv.logintest.entity.ResponseCheckNum;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface SetPwdModle {
    void setPwd(String numbers,SetCallBack setCallBack);
    interface SetCallBack{
        void setSuccess();
        void SetFaild(ResponseCheckNum responseCheckNum);
    }
}

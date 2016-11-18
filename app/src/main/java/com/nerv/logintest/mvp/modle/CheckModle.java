package com.nerv.logintest.mvp.modle;

import com.nerv.logintest.entity.ResponseCheckNum;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface CheckModle {
    void doCheck(String numberAndCheckNum,ChekCallBack chekCallBack);
    interface ChekCallBack{
        void right(String checkNum);
        void erorr(ResponseCheckNum responseCheckNum);
    }
}

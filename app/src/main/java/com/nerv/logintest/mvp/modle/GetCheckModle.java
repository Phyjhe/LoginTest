package com.nerv.logintest.mvp.modle;

import com.nerv.logintest.entity.ResponseCheckNum;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface GetCheckModle {
    void loadDatas(String number,GetCheckCallBack getCheckCallBack);
    void loadNextPage(String number,GetCheckCallBack getCheckCallBack);
    interface GetCheckCallBack{
        void getSuccess(ResponseCheckNum responseCheckNum);
        void getFaild(ResponseCheckNum responseCheckNum);
    }
}

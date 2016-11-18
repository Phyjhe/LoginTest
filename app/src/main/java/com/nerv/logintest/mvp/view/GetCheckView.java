package com.nerv.logintest.mvp.view;

import com.nerv.logintest.entity.ResponseCheckNum;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface GetCheckView {
    void showSuccess(ResponseCheckNum responseCheckNum);
    void showFaild(ResponseCheckNum responseCheckNum);
    void showNextSuccess(ResponseCheckNum responseCheckNum);
    void showNextFaild(ResponseCheckNum responseCheckNum);
}

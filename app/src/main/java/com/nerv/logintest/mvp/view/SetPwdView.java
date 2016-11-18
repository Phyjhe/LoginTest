package com.nerv.logintest.mvp.view;

import com.nerv.logintest.entity.ResponseCheckNum;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface SetPwdView {
    void setSuccess();
    void setFaild(ResponseCheckNum responseCheckNum);
}

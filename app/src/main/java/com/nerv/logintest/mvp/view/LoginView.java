package com.nerv.logintest.mvp.view;

import com.nerv.logintest.entity.ResponseLogin;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface LoginView {
    void showSuccess();
    void showFaild(ResponseLogin responseLogin);
}

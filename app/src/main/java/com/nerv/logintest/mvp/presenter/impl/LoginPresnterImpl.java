package com.nerv.logintest.mvp.presenter.impl;

import com.nerv.logintest.entity.ResponseLogin;
import com.nerv.logintest.mvp.modle.LoginModle;
import com.nerv.logintest.mvp.modle.impl.LoginMoldeImpl;
import com.nerv.logintest.mvp.presenter.LoginPresnter;
import com.nerv.logintest.mvp.view.LoginView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginPresnterImpl implements LoginPresnter {
    private LoginModle modle;
    private LoginView view;
    public LoginPresnterImpl(LoginView view) {
        this.view = view;
        this.modle=new LoginMoldeImpl();
    }

    @Override
    public void loadDatas(String nameAndPwd) {
        modle.loadDatas(nameAndPwd, new LoginModle.LoginCallBack() {
            @Override
            public void loginSuccess() {
                view.showSuccess();
            }

            @Override
            public void loginFaild(ResponseLogin responseLogin) {
                view.showFaild(responseLogin);
            }
        });
    }
}

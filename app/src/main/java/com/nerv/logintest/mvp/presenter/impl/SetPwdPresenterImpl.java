package com.nerv.logintest.mvp.presenter.impl;

import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.modle.SetPwdModle;
import com.nerv.logintest.mvp.modle.impl.SetPwdImpl;
import com.nerv.logintest.mvp.presenter.SetPwdPresenter;
import com.nerv.logintest.mvp.view.SetPwdView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class SetPwdPresenterImpl implements SetPwdPresenter {
    private SetPwdView view;
    private SetPwdModle modle;

    public SetPwdPresenterImpl(SetPwdView view) {
        this.view = view;
        this.modle=new SetPwdImpl();
    }

    @Override
    public void setPwd(String numbers) {
        modle.setPwd(numbers, new SetPwdModle.SetCallBack() {
            @Override
            public void setSuccess() {
                view.setSuccess();
            }

            @Override
            public void SetFaild(ResponseCheckNum responseCheckNum) {
                view.setFaild(responseCheckNum);
            }
        });
    }
}

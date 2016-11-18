package com.nerv.logintest.mvp.presenter.impl;

import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.modle.CheckModle;
import com.nerv.logintest.mvp.modle.impl.CheckModleImpl;
import com.nerv.logintest.mvp.presenter.CheckPresenter;
import com.nerv.logintest.mvp.view.CheckView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class CheckPresenterImpl implements CheckPresenter {
    private CheckView view;
    private CheckModle modle;

    public CheckPresenterImpl(CheckView view) {
        this.view = view;
        this.modle=new CheckModleImpl();
    }

    @Override
    public void doCheck(String numberAndCheckNum) {
        modle.doCheck(numberAndCheckNum, new CheckModle.ChekCallBack() {
            @Override
            public void right(String checkNum) {
                view.showRight(checkNum);
            }

            @Override
            public void erorr(ResponseCheckNum responseCheckNum) {
                view.showErorr(responseCheckNum);
            }
        });
    }
}

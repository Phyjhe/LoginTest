package com.nerv.logintest.mvp.presenter.impl;

import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.modle.GetCheckModle;
import com.nerv.logintest.mvp.modle.impl.GetCheckModleImpl;
import com.nerv.logintest.mvp.presenter.GetCheckPresenter;
import com.nerv.logintest.mvp.view.GetCheckView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class GetCheckPresenterImpl implements GetCheckPresenter {
    private GetCheckModle modle;
    private GetCheckView view;

    public GetCheckPresenterImpl(GetCheckView view) {
        this.view = view;
        this.modle=new GetCheckModleImpl();
    }

    @Override
    public void loadDatas(String number) {
        modle.loadDatas(number, new GetCheckModle.GetCheckCallBack() {
            @Override
            public void getSuccess(ResponseCheckNum responseCheckNum) {
                view.showSuccess(responseCheckNum);
            }

            @Override
            public void getFaild(ResponseCheckNum responseCheckNum) {
                view.showFaild(responseCheckNum);
            }
        });
    }

    @Override
    public void loadNextPage(String number) {
        modle.loadNextPage(number, new GetCheckModle.GetCheckCallBack() {
            @Override
            public void getSuccess(ResponseCheckNum responseCheckNum) {
                view.showNextSuccess(responseCheckNum);
            }

            @Override
            public void getFaild(ResponseCheckNum responseCheckNum) {
                view.showNextFaild(responseCheckNum);
            }
        });
    }
}

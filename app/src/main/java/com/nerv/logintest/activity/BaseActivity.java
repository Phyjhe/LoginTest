package com.nerv.logintest.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2016/11/17.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getContentViewResId());
        initViews();
        initData();
    }

    /**
     * 提供Activity要绑定的xml资源id
     * @return
     */
    protected abstract int getContentViewResId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initViews();
}

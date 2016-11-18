package com.nerv.logintest.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nerv.logintest.R;
import com.nerv.logintest.fragment.BaseFragment;
import com.nerv.logintest.fragment.LoginFragment;
import com.nerv.logintest.fragment.RegistFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private List<Fragment>fragments;

    @BindView(R.id.main_next)
    Button mainRegist;
    @BindView(R.id.main_get)
    Button mainLogin;
    @BindView(R.id.main_framlatout)
    FrameLayout mainFramlatout;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        switchFragment(0);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        fragments=new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegistFragment());
        switchFragment(0);
        mainRegist.setOnClickListener(this);
        mainLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_next:
                switchFragment(0);
                break;
            case R.id.main_get:
                switchFragment(1);
                break;
        }
    }
    private BaseFragment lastFragment;
    private void switchFragment(int index){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        BaseFragment fragment= (BaseFragment) fragments.get(index);
        if (fragment==lastFragment){
            return;
        }
        if (fragment.isAdded()){
            transaction.show(fragment);
        }else{
            transaction.add(R.id.main_framlatout,fragment);
        }
        if (lastFragment!=null){
            transaction.hide( lastFragment);
        }
        transaction.commit();
        lastFragment= fragment;
    }

}

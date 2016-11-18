package com.nerv.logintest.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nerv.logintest.R;
import com.nerv.logintest.activity.WelcomeActicity;
import com.nerv.logintest.entity.ResponseLogin;
import com.nerv.logintest.mvp.presenter.LoginPresnter;
import com.nerv.logintest.mvp.presenter.impl.LoginPresnterImpl;
import com.nerv.logintest.mvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */


public class LoginFragment extends BaseFragment implements LoginView,TextWatcher{


    @BindView(R.id.main_login_name)
    EditText mainLoginName;
    @BindView(R.id.main_login_pwd)
    EditText mainLoginPwd;
    @BindView(R.id.main_login)
    Button mainLogin;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private LoginPresnter presnter;
    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initDatas() {
        presnter=new LoginPresnterImpl(this);
        mainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mainLoginName.getText().toString())||TextUtils.isEmpty(mainLoginPwd.getText().toString())){
                    return;
                }
                presnter.loadDatas(mainLoginName.getText().toString()+"_"+mainLoginPwd.getText().toString());
            }
        });
    }

    @Override
    protected void initViews() {
        mainLoginName.addTextChangedListener(this);
        mainLoginPwd.addTextChangedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showSuccess() {
        startActivity(new Intent(getActivity(), WelcomeActicity.class));
        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFaild(ResponseLogin responseLogin) {
        Toast.makeText(getActivity(), responseLogin.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(mainLoginName.getText().toString())||TextUtils.isEmpty(mainLoginPwd.getText().toString())){
            mainLogin.setBackgroundResource(R.color.half);
            return;
        }
        mainLogin.setBackgroundResource(R.color.all);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

package com.nerv.logintest.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nerv.logintest.R;
import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.presenter.SetPwdPresenter;
import com.nerv.logintest.mvp.presenter.impl.SetPwdPresenterImpl;
import com.nerv.logintest.mvp.view.SetPwdView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ResetPwdActivity extends BaseActivity implements TextWatcher,View.OnClickListener,SetPwdView{
    private String checkNum;
    private SetPwdPresenter pwdPresenter;
    @BindView(R.id.main_regist_number)
    EditText pwd1;
    @BindView(R.id.main_regist_check)
    EditText pwd2;
    @BindView(R.id.main_get)
    Button mainGet;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_resetpwd;
    }

    @Override
    protected void initData() {
        pwdPresenter=new SetPwdPresenterImpl(this);
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        mainGet.setOnClickListener(this);
        pwd1.addTextChangedListener(this);
        pwd2.addTextChangedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        checkNum=getIntent().getStringExtra("checkNum")+"_";
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if ((getCount(pwd1)==getCount(pwd2))&&getCount(pwd1)>5){
//        if (!isEditEmpty(pwd1)&&!isEditEmpty(pwd2)&&count>5){
            mainGet.setBackgroundResource(R.color.all);
            mainGet.setEnabled(true);
        }else {
            mainGet.setBackgroundResource(R.color.half);
            mainGet.setEnabled(false);
        }
    }
    private int getCount(EditText editText){
        return editText.getText().toString().length();
    }
    private boolean isEditEmpty(EditText editText){
        if (TextUtils.isEmpty(editText.getText().toString())){
             Log.d("ResetPwdActivity", "我是来做测试的");
            return true;
        }
        return false;
    }
    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        String s=checkNum+pwd1.getText().toString();
        Log.d("ResetPwdActivity", s);
        if (pwd1.getText().toString().equals(pwd2.getText().toString())){
            pwdPresenter.setPwd(checkNum+pwd1.getText().toString());
        }else {
            Toast.makeText(this, "两次密码不符", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setFaild(ResponseCheckNum responseCheckNum) {
        Toast.makeText(this, responseCheckNum.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

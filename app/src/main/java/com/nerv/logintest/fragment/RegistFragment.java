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
import com.nerv.logintest.activity.ResetPwdActivity;
import com.nerv.logintest.entity.ResponseCheckNum;
import com.nerv.logintest.mvp.presenter.CheckPresenter;
import com.nerv.logintest.mvp.presenter.GetCheckPresenter;
import com.nerv.logintest.mvp.presenter.impl.CheckPresenterImpl;
import com.nerv.logintest.mvp.presenter.impl.GetCheckPresenterImpl;
import com.nerv.logintest.mvp.view.CheckView;
import com.nerv.logintest.mvp.view.GetCheckView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class RegistFragment extends BaseFragment implements TextWatcher,View.OnClickListener,GetCheckView,CheckView{
    private CheckPresenter checkPresenter;
    private GetCheckPresenter presenter;
    @BindView(R.id.main_regist_number)
    EditText mainRegistNumber;
    @BindView(R.id.main_regist_pwd)
    EditText mainRegistPwd;
    @BindView(R.id.fragment_next)
    Button fragmentNext;
    @BindView(R.id.fangment_check)
    Button fangmentCheck;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_regist;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        checkPresenter=new CheckPresenterImpl(this);
        presenter=new GetCheckPresenterImpl(this);
        mainRegistNumber.addTextChangedListener(this);
        mainRegistPwd.addTextChangedListener(this);
        fragmentNext.setOnClickListener(this);
        fangmentCheck.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(TextUtils.isEmpty(mainRegistNumber.getText().toString())){
            fangmentCheck.setBackgroundResource(R.color.half);
            fangmentCheck.setEnabled(false);
        }else{
            fangmentCheck.setBackgroundResource(R.color.all);
            fangmentCheck.setEnabled(true);
        }
        if (!TextUtils.isEmpty(mainRegistPwd.getText().toString())&&!TextUtils.isEmpty(mainRegistNumber.getText().toString())){
            fragmentNext.setBackgroundResource(R.color.all);
            fragmentNext.setEnabled(true);
        }else {
            fragmentNext.setBackgroundResource(R.color.half);
            fragmentNext.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_next:
                checkPresenter.doCheck(mainRegistNumber.getText().toString()+"_"+mainRegistPwd.getText().toString());
                break;
            case R.id.fangment_check:
                presenter.loadDatas(mainRegistNumber.getText().toString());
                break;
        }
    }

    @Override
    public void showSuccess(ResponseCheckNum responseCheckNum) {
        presenter.loadNextPage(mainRegistNumber.getText().toString());
    }

    @Override
    public void showFaild(ResponseCheckNum responseCheckNum) {
        Toast.makeText(getActivity(), responseCheckNum.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNextSuccess(ResponseCheckNum responseCheckNum) {
        Toast.makeText(getActivity(), "请在" + responseCheckNum.getTimeleft()+"内输入验证码", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNextFaild(ResponseCheckNum responseCheckNum) {
        Toast.makeText(getActivity(), responseCheckNum.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRight(String checkNum) {
        Intent intent=new Intent(getActivity(), ResetPwdActivity.class);
        intent.putExtra("checkNum",checkNum);
        getActivity().startActivity(intent);
    }

    @Override
    public void showErorr(ResponseCheckNum responseCheckNum) {
        Toast.makeText(getActivity(), responseCheckNum.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

package com.lee.vademovies.view.activity;

import android.os.Bundle;

import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageActivity extends BaseActivity {


    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {
        mUnbinder.unbind();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

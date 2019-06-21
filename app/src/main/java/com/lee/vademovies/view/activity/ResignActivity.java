package com.lee.vademovies.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseActivity;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.model.bean.Result;
import com.lee.vademovies.presenter.ResignPresenter;
import com.lee.vademovies.util.EditTextUtils;
import com.lee.vademovies.util.EncryptUtil;
import com.lee.vademovies.util.ScreenUtils;
import com.lee.vademovies.util.SystemUtil;
import com.lee.vademovies.util.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ResignActivity extends BaseActivity {

    @BindView(R.id.et_name_resign)
    EditText mEtNameResign;     //昵称
    @BindView(R.id.et_sex_resign)
    EditText mEtSexResign;      //性别
    @BindView(R.id.et_birthday_resign)
    EditText mEtBirthdayResign; //生日
    @BindView(R.id.et_phone_resign)
    EditText mEtPhoneResign;    //手机号
    @BindView(R.id.et_email_resign)
    EditText mEtEmailResign;    //邮箱
    @BindView(R.id.et_pwd_resign)
    EditText mEtPwdResign;      //密码
    @BindView(R.id.bt_login_resign)
    Button mBtLoginResign;      //注册按钮
    private Unbinder mUnbinder;

    private ResignPresenter mResignPresenter;
    private int mEtSexResign1;
    //IMEI号
    private String mImei;
    private String Android = "Android";
    //设备类型
    private String mUa;
    //手机厂商
    private String mDeviceBrand;
    //屏幕尺寸
    private double mScreenInch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int checkPermission = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1); //后面的1为请求码
                LogUtils.d("未授权,去授权");
                mImei = SystemUtil.getIMEI(ResignActivity.this);
                LogUtils.i(mImei, "imei");
                return;
            } else {
                LogUtils.d("已授权...");

                mImei = SystemUtil.getIMEI(ResignActivity.this);
                LogUtils.i(mImei, "imei");
            }

        } else {
            LogUtils.d("版本<=8.0");

            mImei = SystemUtil.getIMEI(ResignActivity.this);
            LogUtils.i(mImei, "imei");
        }
        mDeviceBrand = SystemUtil.getDeviceBrand();
        mScreenInch = ScreenUtils.getScreenInch(ResignActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_resign;
    }

    @Override
    protected void initView() {
        EditTextUtils.setEditTextInhibitInputSpace(mEtNameResign);
        EditTextUtils.setEditTextInhibitInputSpace(mEtSexResign);
        EditTextUtils.setEditTextInhibitInputSpace(mEtPhoneResign);
        EditTextUtils.setEditTextInhibitInputSpace(mEtBirthdayResign);
        EditTextUtils.setEditTextInhibitInputSpace(mEtPwdResign);
        EditTextUtils.setEditTextInhibitInputSpace(mEtEmailResign);

        mResignPresenter = new ResignPresenter(new ResignCall());

    }

    @Override
    protected void destoryData() {
        mUnbinder.unbind();
        mResignPresenter.unBind();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @OnClick(R.id.bt_login_resign)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_login_resign:
                if (!RegexUtils.isUsername(mEtNameResign.getText().toString())) {
                    ToastUtils.showShort("请输入正确的昵称");
                    LogUtils.d(mEtNameResign.getText().toString());
                    return;
                }
                if (mEtSexResign.getText().toString().equals("男") || mEtSexResign.getText().toString().equals("女")) {
                    if (mEtSexResign.getText().toString().equals("男")) {
                        mEtSexResign1 = 1;
                    } else {
                        mEtSexResign1 = 2;
                    }
                } else {
                    ToastUtils.showShort("请输入正确的性别");
                    LogUtils.d(mEtSexResign.getText().toString());
                    return;
                }
                if (!RegexUtils.isMobileExact(mEtPhoneResign.getText().toString())) {
                    ToastUtils.showShort("请输入正确的手机号");
                    LogUtils.d(mEtPhoneResign.getText().toString());
                    return;
                }

                if (TextUtils.isEmpty(mEtPwdResign.getText().toString()) || mEtPwdResign.getText().toString().length() < 6 || mEtPwdResign.getText().toString().trim().isEmpty()) {
                    ToastUtils.showShort("请输入密码");
                    LogUtils.d(mEtPwdResign.getText().toString());
                    return;
                }
                if (!BaseActivity.isDate(mEtBirthdayResign.getText().toString())) {
                    ToastUtils.showShort("请输入正确的出生日期");
                    LogUtils.d(mEtBirthdayResign.getText().toString());
                    return;
                }
                if (!RegexUtils.isEmail(mEtEmailResign.getText().toString())) {
                    ToastUtils.showShort("请输入正确的邮箱");
                    LogUtils.d(mEtEmailResign.getText().toString());
                    return;
                }

                LogUtils.d(mEtNameResign.getText().toString(), mEtSexResign1, mEtPhoneResign.getText().toString(),
                        mEtPwdResign.getText().toString(), mEtPwdResign.getText().toString(), mEtBirthdayResign.getText().toString(),
                        mImei, mUa, mScreenInch, Android, mEtEmailResign.getText().toString());

                mResignPresenter.reqeust(mEtNameResign.getText().toString(), mEtPhoneResign.getText().toString(),
                        EncryptUtil.encrypt(mEtPwdResign.getText().toString()), EncryptUtil.encrypt(mEtPwdResign.getText().toString()), mEtSexResign1, mEtBirthdayResign.getText().toString(),
                        mImei, mUa, String.valueOf(mScreenInch), Android, mEtEmailResign.getText().toString());
                break;
        }
    }

    class ResignCall implements DataCall<Result> {

        @Override
        public void onSuccess(Result data, Object... args) {
            if (data.getStatus().equals("0000")) {
                ToastUtils.showShort(data.getMessage());
                Intent intent = new Intent();
                String phoneNum = mEtPhoneResign.getText().toString();
                String phonePwd = mEtPwdResign.getText().toString();
                intent.putExtra("phoneNum", phoneNum);
                intent.putExtra("phonePwd", phonePwd);
                LogUtils.d(phoneNum, phonePwd, "ResignActivity");
                ResignActivity.this.setResult(2, intent);
            } else {
                ToastUtils.showShort(data.getMessage());
            }
            finish();
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            mLoadDialog.cancel();
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }


}

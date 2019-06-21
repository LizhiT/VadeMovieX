package com.lee.vademovies.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lee.vademovies.R;
import com.lee.vademovies.appllication.VadeApplication;
import com.lee.vademovies.base.BaseActivity;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.model.bean.Result;
import com.lee.vademovies.model.bean.UserInfo;
import com.lee.vademovies.presenter.LoginPresenter;
import com.lee.vademovies.util.EditTextUtils;
import com.lee.vademovies.util.EncryptUtil;
import com.lee.vademovies.util.db.DaoMaster;
import com.lee.vademovies.util.db.UserInfoDao;
import com.lee.vademovies.util.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created :  LiZhIX
 * Date :  2019/6/12 17:04
 * Description  :  登录Activity
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_phone_login)
    ImageView mIvPhoneLogin;
    @BindView(R.id.et_phone_login)
    EditText mEtPhoneLogin;
    @BindView(R.id.iv_psd_login)
    ImageView mIvPsdLogin;
    @BindView(R.id.et_psd_login)
    EditText mEtPsdLogin;
    @BindView(R.id.iv_show_pwd_login)
    ImageView mIvShowPwdLogin;
    @BindView(R.id.cb_rem_pwd_login)
    CheckBox mCbRemPwdLogin;
    @BindView(R.id.tv_fast_resign_login)
    TextView mTvFastResignLogin;
    @BindView(R.id.bt_login_login)
    Button mBtLoginLogin;
    @BindView(R.id.iv_wechat_login)
    ImageView mIvWechatLogin;
    private LoginPresenter mLoginPresenter;
    private String mPhoneNum;
    private String mPwd;
    private boolean pasVisibile = false;
    private Unbinder mUnbinder;
    private SharedPreferences mShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        EditTextUtils.setEditTextInhibitInputSpace(mEtPsdLogin);
        mLoginPresenter = new LoginPresenter(new LoginCall());
        mShare = VadeApplication.getShare();

        if (mShare.getBoolean("remPas", false)) {
            //设置默认是记录密码状态
            mCbRemPwdLogin.setChecked(true);
            mEtPhoneLogin.setText(mShare.getString("mobile", ""));
            mEtPsdLogin.setText(mShare.getString("pas", ""));
            //设置默认是自动登录状态
            mCbRemPwdLogin.setChecked(true);
            //跳转界面
            intent(MainActivity.class);
        }

        mCbRemPwdLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCbRemPwdLogin.isChecked()) {
                    VadeApplication.getShare().edit().putBoolean("remPas", mCbRemPwdLogin.isChecked()).commit();
                } else {
                    VadeApplication.getShare().edit().putBoolean("remPas", mCbRemPwdLogin.isChecked()).commit();
                }
            }
        });


    }

    @Override
    protected void destoryData() {
        mLoginPresenter.unBind();
        mUnbinder.unbind();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick({R.id.iv_show_pwd_login, R.id.tv_fast_resign_login, R.id.bt_login_login, R.id.iv_wechat_login})
    public void onClick(View v) {

        mPhoneNum = mEtPhoneLogin.getText().toString();
        mPwd = mEtPsdLogin.getText().toString();

        switch (v.getId()) {
            default:
                break;
            //控制密码的显示隐藏
            case R.id.iv_show_pwd_login:
                if (pasVisibile) {
                    //密码显示，则隐藏
                    mEtPsdLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pasVisibile = false;
                } else {
                    //密码隐藏则显示
                    mEtPsdLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pasVisibile = true;
                }
                break;
            //快速注册
            case R.id.tv_fast_resign_login:
                Intent intent = new Intent(LoginActivity.this, ResignActivity.class);
                startActivityForResult(intent, 1);
                break;
            //登录
            case R.id.bt_login_login:
                if (!RegexUtils.isMobileExact(mPhoneNum)) {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }

                if (TextUtils.isEmpty(mPwd) || mPwd.length() < 6 || mPwd.trim().isEmpty()) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }

                if (mCbRemPwdLogin.isChecked()) {
                    VadeApplication.getShare().edit().putString("mobile", mPhoneNum).putString("pas", mPwd).commit();
                }
                mLoadDialog.show();
                mLoginPresenter.reqeust(mPhoneNum, EncryptUtil.encrypt(mPwd));

                break;
            //微信登录
            //TODO 微信登陆(集成三方)
            case R.id.iv_wechat_login:

                break;
        }
    }

    class LoginCall implements DataCall<Result<UserInfo>> {

        @Override
        public void onSuccess(Result<UserInfo> data, Object... args) {
            mLoadDialog.cancel();
            if (data.getStatus().equals("0000")) {
                data.getResult().setStatus(1);//设置登录状态，保存到数据库
                UserInfoDao userInfoDao = DaoMaster.newDevSession(getBaseContext(), UserInfoDao.TABLENAME).getUserInfoDao();
                userInfoDao.insertOrReplace(data.getResult());
                intent(MainActivity.class);
                finish();
            }
            ToastUtils.showShort(data.getMessage());
            LogUtils.d(data.getMessage(), data.getStatus());
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            mLoadDialog.cancel();
            ToastUtils.showShort(data.getCode() + "   " + data.getDisplayMessage());
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            if (resultCode == 2) { // 对应B里面的标志为成功
                String phoneNum = data.getStringExtra("phoneNum");
                mEtPhoneLogin.setText(phoneNum);
                String phonePwd = data.getStringExtra("phonePwd");
                mEtPsdLogin.setText(phonePwd);
                LogUtils.d(phoneNum, phonePwd, "LoginActivity");
            }
            LogUtils.d("Code", requestCode, resultCode, data);
        }
    }
}

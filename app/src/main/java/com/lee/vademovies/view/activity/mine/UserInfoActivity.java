package com.lee.vademovies.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 15:04
 * Description  :  用户信息列表
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.iv_icon_info)
    ImageView mIvIconInfo;
    @BindView(R.id.tv_name_info)
    TextView mTvNameInfo;
    @BindView(R.id.tv_sex_info)
    TextView mTvSexInfo;
    @BindView(R.id.tv_birthday_info)
    TextView mTvBirthdayInfo;
    @BindView(R.id.tv_tel_info)
    TextView mTvTelInfo;
    @BindView(R.id.tv_email_info)
    TextView mTvEmailInfo;
    @BindView(R.id.rl_reset_pwd_info)
    RelativeLayout mRlResetPwdInfo;
    @BindView(R.id.iv_info_back)
    ImageView mIvInfoBack;
    @BindView(R.id.rl_reset_mine_info)
    RelativeLayout mRlResetMineInfo;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
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

    @OnClick({R.id.rl_reset_pwd_info, R.id.iv_info_back, R.id.rl_reset_mine_info})
    public void onClick(View v) {
        switch (v.getId()) {
            //修改密码
            case R.id.rl_reset_pwd_info:
                intent(ResetPwdActivity.class);
                break;
            case R.id.iv_info_back:
                finish();
                break;
            //修改个人信息
            case R.id.rl_reset_mine_info:
                intent(ResetInfoActivity.class);
                break;
        }
    }
}

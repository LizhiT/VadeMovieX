package com.lee.vademovies.view.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseActivity;
import com.lee.vademovies.util.LocationUtils;
import com.lee.vademovies.util.permisstion.Permission;
import com.lee.vademovies.view.fragment.home.HomeFragment;
import com.lee.vademovies.view.fragment.home.MineFragment;
import com.lee.vademovies.view.fragment.home.StudiosFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 16:00
 * Description  :  主页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_home_main)
    ImageView mIvHomeMain;
    @BindView(R.id.iv_cinema_main)
    ImageView mIvCinemaMain;
    @BindView(R.id.iv_mine_main)
    ImageView mIvMineMain;
    @BindView(R.id.tv_location_home)
    TextView mTvLocationHome;
    @BindView(R.id.rl_location_home)
    RelativeLayout mRlLocationHome;
    @BindView(R.id.rl_search_home)
    RelativeLayout mRlSearchHome;
    @BindView(R.id.iv_remind_home)
    ImageView mIvRemindHome;
    @BindView(R.id.fl_home)
    FrameLayout mFlHome;
    @BindView(R.id.et_search_home)
    EditText mEtSearchHome;
    @BindView(R.id.tv_search_home)
    TextView mTvSearchHome;
    private Unbinder mUnbinder;
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;
    private StudiosFragment mStudiosFragment;
    private boolean mRlSearchHomeStatus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Location location = LocationUtils.getInstance(MainActivity.this).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            LogUtils.d("FLY.LocationUtils", address);
            mTvLocationHome.setText(address);
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mSupportFragmentManager = getSupportFragmentManager();
        mHomeFragment = new HomeFragment();
        AnimatorSet mSet = new AnimatorSet();
        mIvHomeMain.setImageResource(R.drawable.film_selected);
        ObjectAnimator o1 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleX", 1.17f);
        ObjectAnimator o4 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleY", 1.17f);
        //存入集合
        mSet.playTogether(o1, o4);
        //开始执行
        mSet.start();
        mSupportFragmentManager.beginTransaction().add(R.id.fl_home, mHomeFragment).commit();

    }

    @Override
    protected void destoryData() {
        LocationUtils.getInstance(this).removeLocationUpdatesListener();
        mUnbinder.unbind();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //TODO  点击切换Fragment 页面可以改变 但是按钮UI没有改变 第二次点才改变
    @Permission(permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    @OnClick({R.id.iv_home_main, R.id.iv_cinema_main, R.id.iv_mine_main, R.id.iv_search_home, R.id.iv_remind_home, R.id.tv_search_home})
    public void onClick(View v) {
        mFragmentTransaction = mSupportFragmentManager.beginTransaction();

        if (mHomeFragment != null) {
            mFragmentTransaction.hide(mHomeFragment);
        }
        if (mStudiosFragment != null) {
            mFragmentTransaction.hide(mStudiosFragment);
        }
        if (mMineFragment != null) {
            mFragmentTransaction.hide(mMineFragment);
        }

        switch (v.getId()) {
            default:
                break;
            case R.id.iv_home_main:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    mFragmentTransaction.add(R.id.fl_home, mHomeFragment);
                } else {
                    isImageHome(mFragmentTransaction);
                }
                break;
            case R.id.iv_cinema_main:
                if (mStudiosFragment == null) {
                    mStudiosFragment = new StudiosFragment();
                    mFragmentTransaction.add(R.id.fl_home, mStudiosFragment);
                } else {
                    isImageCinema(mFragmentTransaction);
                }
                break;
            case R.id.iv_mine_main:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    mFragmentTransaction.add(R.id.fl_home, mMineFragment);
                } else {
                    isImageMine(mFragmentTransaction);
                }
                break;
            case R.id.iv_search_home:       //搜索
                openSearch();
                break;
            case R.id.iv_remind_home:       //提醒
                intent(MessageActivity.class);
                break;
            case R.id.tv_search_home:
                ToastUtils.showShort(R.string.toast_gps_not_found);
                mEtSearchHome.setText(null);
                closeSearch();
                break;
        }
        mFragmentTransaction.commit();
    }

    //首页切换
    private void isImageHome(FragmentTransaction transaction) {
        mIvHomeMain.setImageResource(R.drawable.film_selected);
        mIvCinemaMain.setImageResource(R.drawable.cinema_not_select);
        mIvMineMain.setImageResource(R.drawable.my_not_select);

        //属性动画图片大小
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator o1 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleX", 1.17f);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(mIvCinemaMain, "scaleX", 1.0f);
        ObjectAnimator o3 = ObjectAnimator.ofFloat(mIvMineMain, "scaleX", 1.0f);

        ObjectAnimator o4 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleY", 1.17f);
        ObjectAnimator o5 = ObjectAnimator.ofFloat(mIvCinemaMain, "scaleY", 1.0f);
        ObjectAnimator o6 = ObjectAnimator.ofFloat(mIvMineMain, "scaleY", 1.0f);
        //存入集合
        set.playTogether(o1, o2, o3, o4, o5, o6);
        //开始执行
        set.start();

        transaction.show(mHomeFragment);
        mIvRemindHome.setVisibility(View.GONE);
        mRlLocationHome.setVisibility(View.VISIBLE);
        mRlSearchHome.setVisibility(View.VISIBLE);
    }

    //影院页切换
    public void isImageCinema(FragmentTransaction transaction) {
        mIvHomeMain.setImageResource(R.drawable.film_not_select);
        mIvCinemaMain.setImageResource(R.drawable.cinema_selected);
        mIvMineMain.setImageResource(R.drawable.my_not_select);

        //属性动画改变图片大小
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator o1 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleX", 1.0f);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(mIvCinemaMain, "scaleX", 1.17f);
        ObjectAnimator o3 = ObjectAnimator.ofFloat(mIvMineMain, "scaleX", 1.0f);

        ObjectAnimator o4 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleY", 1.0f);
        ObjectAnimator o5 = ObjectAnimator.ofFloat(mIvCinemaMain, "scaleY", 1.17f);
        ObjectAnimator o6 = ObjectAnimator.ofFloat(mIvMineMain, "scaleY", 1.0f);
        //存入集合
        set.playTogether(o1, o2, o3, o4, o5, o6);
        //开始执行
        set.start();

        transaction.show(mStudiosFragment);
        mIvRemindHome.setVisibility(View.GONE);
        mRlLocationHome.setVisibility(View.VISIBLE);
        mRlSearchHome.setVisibility(View.VISIBLE);


    }

    //我的页面切换
    private void isImageMine(FragmentTransaction transaction) {
        //切换图片
        mIvHomeMain.setImageResource(R.drawable.film_not_select);
        mIvCinemaMain.setImageResource(R.drawable.cinema_not_select);
        mIvMineMain.setImageResource(R.drawable.my_selected);

        //属性动画改变图片大小
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator o1 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleX", 1.0f);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(mIvCinemaMain, "scaleX", 1.0f);
        ObjectAnimator o3 = ObjectAnimator.ofFloat(mIvMineMain, "scaleX", 1.17f);

        ObjectAnimator o4 = ObjectAnimator.ofFloat(mIvHomeMain, "scaleY", 1.0f);
        ObjectAnimator o5 = ObjectAnimator.ofFloat(mIvCinemaMain, "scaleY", 1.0f);
        ObjectAnimator o6 = ObjectAnimator.ofFloat(mIvMineMain, "scaleY", 1.17f);

        //存入集合
        set.playTogether(o1, o2, o3, o4, o5, o6);
        //开始执行
        set.start();

        //显示和隐藏
        transaction.show(mMineFragment);
        mIvRemindHome.setVisibility(View.VISIBLE);
        mRlLocationHome.setVisibility(View.GONE);
        mRlSearchHome.setVisibility(View.GONE);

    }

    //打开搜索框
    private void openSearch() {
        if (mRlSearchHomeStatus) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRlSearchHome, "translationX", 0, (dp2px(MainActivity.this, -170)));
            ObjectAnimator alphaEt = ObjectAnimator.ofFloat(mEtSearchHome, "alpha", 0.0f, 1.0f);
            ObjectAnimator alphaTv = ObjectAnimator.ofFloat(mTvSearchHome, "alpha", 0.0f, 1.0f);
            alphaTv.setDuration(1000);
            mTvSearchHome.setVisibility(View.VISIBLE);
            alphaTv.start();
            alphaEt.setDuration(1000);
            mEtSearchHome.setVisibility(View.VISIBLE);
            alphaEt.start();
            //动画时间
            objectAnimator.setDuration(1000);
            objectAnimator.start();
            mRlSearchHomeStatus = !mRlSearchHomeStatus;
        }
    }

    //收缩搜索框
    private void closeSearch() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRlSearchHome, "translationX", (dp2px(MainActivity.this, -170)), 0);
        ObjectAnimator alphaEt = ObjectAnimator.ofFloat(mEtSearchHome, "alpha", 1.0f, 0.5f, 0.0f);
        ObjectAnimator alphaTv = ObjectAnimator.ofFloat(mTvSearchHome, "alpha", 1.0f, 0.5f, 0.0f);
        alphaTv.setDuration(1000);
        alphaTv.start();
        alphaEt.setDuration(1000);
        alphaEt.start();
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        mRlSearchHomeStatus = !mRlSearchHomeStatus;
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}

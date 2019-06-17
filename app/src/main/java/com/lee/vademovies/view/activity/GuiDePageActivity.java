package com.lee.vademovies.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lee.vademovies.R;
import com.lee.vademovies.view.adapter.MyFragmentPagerAdapter;
import com.lee.vademovies.view.fragment.welcome.WelcomeFourFragmnet;
import com.lee.vademovies.view.fragment.welcome.WelcomeOneFragmnet;
import com.lee.vademovies.view.fragment.welcome.WelcomeThreeFragmnet;
import com.lee.vademovies.view.fragment.welcome.WelcomeTwoFragmnet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 16:37
 * Description  :  引导页
 */
public class GuiDePageActivity extends AppCompatActivity {

    @BindView(R.id.vp_guide)
    ViewPager mVpGuide;
    @BindView(R.id.rb_1)
    RadioButton mRb1;
    @BindView(R.id.rb_2)
    RadioButton mRb2;
    @BindView(R.id.rb_3)
    RadioButton mRb3;
    @BindView(R.id.rb_4)
    RadioButton mRb4;
    @BindView(R.id.rg_guide)
    RadioGroup mRgGuide;
    private Unbinder mUnbinder;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_de_page);
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        mFragments = new ArrayList<>();
        WelcomeOneFragmnet welcomeOneFragmnet = new WelcomeOneFragmnet();
        WelcomeTwoFragmnet welcomeTwoFragmnet = new WelcomeTwoFragmnet();
        WelcomeThreeFragmnet welcomeThreeFragmnet = new WelcomeThreeFragmnet();
        WelcomeFourFragmnet welcomefourFragmnet = new WelcomeFourFragmnet();
        mFragments.add(welcomeOneFragmnet);
        mFragments.add(welcomeTwoFragmnet);
        mFragments.add(welcomeThreeFragmnet);
        mFragments.add(welcomefourFragmnet);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mVpGuide.setAdapter(adapter);
        mVpGuide.setOffscreenPageLimit(2);
    }

    private void initData() {
        mVpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int mCurrentItem;

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentItem = i;
                switch (i) {
                    case 0:
                        mRgGuide.check(R.id.rb_1);
                        mRb1.setBackgroundResource(R.drawable.shape_radio);
                        mRb2.setBackgroundResource(R.drawable.shape_radio2);
                        mRb3.setBackgroundResource(R.drawable.shape_radio2);
                        mRb4.setBackgroundResource(R.drawable.shape_radio2);
                        break;
                    case 1:
                        mRgGuide.check(R.id.rb_2);
                        mRb2.setBackgroundResource(R.drawable.shape_radio);
                        mRb1.setBackgroundResource(R.drawable.shape_radio2);
                        mRb4.setBackgroundResource(R.drawable.shape_radio2);
                        mRb4.setBackgroundResource(R.drawable.shape_radio2);
                        break;
                    case 2:
                        mRgGuide.check(R.id.rb_3);
                        mRb3.setBackgroundResource(R.drawable.shape_radio);
                        mRb2.setBackgroundResource(R.drawable.shape_radio2);
                        mRb1.setBackgroundResource(R.drawable.shape_radio2);
                        mRb4.setBackgroundResource(R.drawable.shape_radio2);
                        break;
                    case 3:
                        mRgGuide.check(R.id.rb_4);
                        mRb4.setBackgroundResource(R.drawable.shape_radio);
                        mRb2.setBackgroundResource(R.drawable.shape_radio2);
                        mRb3.setBackgroundResource(R.drawable.shape_radio2);
                        mRb1.setBackgroundResource(R.drawable.shape_radio2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
}

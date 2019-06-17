package com.lee.vademovies.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lee.vademovies.R;
import com.lee.vademovies.appllication.VadeApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created :  LiZhIX
 * Date :  2019/6/12 16:37
 * Description  :  欢迎页面
 */
public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.welcome_bottom_text)
    TextView mWelcomeBottomText;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mUnbinder = ButterKnife.bind(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    date();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //记录是否第一次进入App
    private void date() {
        boolean islogin = VadeApplication.getShare().getBoolean("islogin", true);
        SharedPreferences.Editor edit = VadeApplication.getShare().edit();
        if (islogin) {
            //第一次进入跳转
            Intent in = new Intent(WelcomeActivity.this, GuiDePageActivity.class);
            startActivity(in);
            finish();
            edit.putBoolean("islogin", false);
            edit.commit();
        } else {
            //第二次进入跳转
            Intent in = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }
    }

    //沉浸式
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

}

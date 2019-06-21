package com.lee.vademovies.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.et_note_feed)
    EditText mEtNoteFeed;
    @BindView(R.id.bt_submit_feed)
    Button mBtSubmitFeed;
    @BindView(R.id.iv_feed_back)
    ImageView mIvFeedBack;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {
        mUnbinder.unbind();
    }

    @OnClick({R.id.bt_submit_feed, R.id.iv_feed_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit_feed:

                break;
            case R.id.iv_feed_back:
                finish();
                break;
        }
    }
}

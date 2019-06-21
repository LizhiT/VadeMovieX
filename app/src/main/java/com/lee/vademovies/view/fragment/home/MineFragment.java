package com.lee.vademovies.view.fragment.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lee.vademovies.R;
import com.lee.vademovies.appllication.VadeApplication;
import com.lee.vademovies.base.BaseFragment;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.model.bean.LoginBean;
import com.lee.vademovies.model.bean.Result;
import com.lee.vademovies.presenter.MinePresenter;
import com.lee.vademovies.util.exception.ApiException;
import com.lee.vademovies.view.activity.LoginActivity;
import com.lee.vademovies.view.activity.MessageActivity;
import com.lee.vademovies.view.activity.mine.AttentionActivity;
import com.lee.vademovies.view.activity.mine.FeedBackActivity;
import com.lee.vademovies.view.activity.mine.TicketHistoryActivity;
import com.lee.vademovies.view.activity.mine.UserInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created :  LiZhIX
 * Date :  2019/6/14 16:27
 * Description  :  我的主页Fragment
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.iv_remind_home)
    ImageView mIvRemindHome;
    @BindView(R.id.icon_head)
    ImageView mIconHead;
    @BindView(R.id.mine_text)
    TextView mMineText;
    @BindView(R.id.sign)
    Button mSign;
    @BindView(R.id.rl_info_mine)
    RelativeLayout mRlInfoMine;
    @BindView(R.id.rl_attention_mine)
    RelativeLayout mRlAttentionMine;
    @BindView(R.id.rl_ticket_mine)
    RelativeLayout mRlTicketMine;
    @BindView(R.id.rl_feedback_mine)
    RelativeLayout mRlFeedbackMine;
    @BindView(R.id.rl_version_mine)
    RelativeLayout mRlVersionMine;
    @BindView(R.id.rl_exit_mine)
    RelativeLayout mRlExitMine;
    private View view;
    private Unbinder unbinder;
    private MinePresenter mMinePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        initView();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mMinePresenter = null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        mMinePresenter = new MinePresenter(new MineDataCall());
        mMinePresenter.reqeust();
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            //更新界面数据，如果数据还在下载中，就显示加载框


        } else {
            //关闭加载框
            mLoadDialog.cancel();
        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }


    @OnClick({R.id.iv_remind_home, R.id.sign, R.id.rl_info_mine, R.id.rl_attention_mine, R.id.rl_ticket_mine, R.id.rl_feedback_mine, R.id.rl_version_mine, R.id.rl_exit_mine})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_remind_home:
                intent(MessageActivity.class);
                break;
            case R.id.sign:
                break;
            case R.id.rl_info_mine:
                intent(UserInfoActivity.class);
                break;
            case R.id.rl_attention_mine:
                intent(AttentionActivity.class);
                break;
            case R.id.rl_ticket_mine:
                intent(TicketHistoryActivity.class);
                break;
            case R.id.rl_feedback_mine:
                intent(FeedBackActivity.class);
                break;
            //TODO  判断版本 如果不符 提示更新
            case R.id.rl_version_mine:

                break;
            //TODO 退出登录
            case R.id.rl_exit_mine:
                // 退出登录 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //    设置Title的内容
                builder.setTitle("温馨提示");
                builder.setMessage("确定退出登录吗？");
                builder.setPositiveButton("确认退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("暂不退出", null);
                builder.show();
                VadeApplication.getShare().edit().clear().commit();
                break;
        }
    }


    class MineDataCall implements DataCall<Result<LoginBean.ResultBean.UserInfoBean>> {

        @Override
        public void onSuccess(Result<LoginBean.ResultBean.UserInfoBean> data, Object... args) {
            LogUtils.d(data.getMessage());
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());

        }
    }

}
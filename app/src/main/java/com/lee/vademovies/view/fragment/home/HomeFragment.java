package com.lee.vademovies.view.fragment.home;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseFragment;
import com.lee.vademovies.bean.HotBean;
import com.lee.vademovies.bean.HotWillBean;
import com.lee.vademovies.bean.HotingBean;
import com.lee.vademovies.bean.Result;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.presenter.HomeHotPresenter;
import com.lee.vademovies.presenter.HomeHotWillPresenter;
import com.lee.vademovies.presenter.HomeHotingPresenter;
import com.lee.vademovies.util.exception.ApiException;
import com.lee.vademovies.view.adapter.BannerHomeAdapter;
import com.lee.vademovies.view.adapter.HotRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * Created :  LiZhIX
 * Date :  2019/6/14 16:27
 * Description  :  主页Fragment
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.rcf_home)
    RecyclerCoverFlow mRcfHome;
    @BindView(R.id.iv_more1_home)
    ImageView mIvMore1Home;
    @BindView(R.id.rv1_home)
    RecyclerView mRv1Home;
    @BindView(R.id.iv_more2_home)
    ImageView mIvMore2Home;
    @BindView(R.id.rv2_home)
    RecyclerView mRv2Home;
    @BindView(R.id.iv_more3_home)
    ImageView mIvMore3Home;
    @BindView(R.id.rv3_home)
    RecyclerView mRv3Home;
    @BindView(R.id.movie_text_xian)
    TextView mMovieTextXian;
    @BindView(R.id.movie_text_dong)
    TextView mMovieTextDong;
    private View mView;
    private Unbinder unbinder;
    private int mCoun;
    List<String> textList = new ArrayList<>();
    List<Integer> imgList = new ArrayList<>();
    private int page = 1;
    private int count = 10;
    private HomeHotPresenter mHomePresenter;
    private HomeHotingPresenter mHomeHotingPresenter;
    private HomeHotWillPresenter mHomeHotWillPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_home, null);

        unbinder = ButterKnife.bind(this, mView);

        initView();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mHomePresenter.unBind();
        mHomeHotingPresenter.unBind();
        mHomeHotWillPresenter.unBind();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        imgList.add(R.drawable.weinixieshi);
        imgList.add(R.drawable.zuoriqinkong);
        imgList.add(R.drawable.ttiexuezhanshi);
        imgList.add(R.drawable.wushuang);
        imgList.add(R.drawable.guojiang);


        LogUtils.d(imgList);
        textList.add("为你写诗");
        textList.add("昨日青空");
        textList.add("铁血战士");
        textList.add("无双");
        textList.add("猛虫过江");

        BannerHomeAdapter bannerHomeAdapter = new BannerHomeAdapter(getContext(), imgList, textList);
        mRcfHome.setAdapter(bannerHomeAdapter);
        int itemCount = bannerHomeAdapter.getItemCount();
        int mWidth = mMovieTextXian.getWidth();
        mCoun = mWidth / itemCount;
        mRcfHome.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                int selectedPos = mRcfHome.getSelectedPos();
                ObjectAnimator animator = ObjectAnimator.ofFloat(mMovieTextDong, "translationX", mCoun * (selectedPos));
                animator.setDuration(500);
                animator.start();
            }
        });

        mHomePresenter = new HomeHotPresenter(new HomeCall());
        mHomeHotingPresenter = new HomeHotingPresenter(new HomeHotingCall());
        mHomeHotWillPresenter = new HomeHotWillPresenter(new HotWillCall());

        mHomePresenter.reqeust(page, count);
        mHomeHotingPresenter.reqeust(page, count);
        mHomeHotWillPresenter.reqeust(page, count);

        mRv1Home.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRv2Home.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRv3Home.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
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

    //第一次要加载的数据
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }

    class HomeCall implements DataCall<Result<HotBean>> {

        @Override
        public void onSuccess(Result<HotBean> data, Object... args) {
            if (data.getStatus().equals("0000")) {
                LogUtils.d(data.getResult().getResult().toString());
                HotRecyclerViewAdapter hotRecyclerViewAdapter = new HotRecyclerViewAdapter(getActivity(), data.getResult());
                mRv1Home.setAdapter(hotRecyclerViewAdapter);
            }
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }

    class HomeHotingCall implements DataCall<Result<HotingBean>> {

        @Override
        public void onSuccess(Result<HotingBean> data, Object... args) {
            if (data.getStatus().equals("0000")) {


            } else {
                ToastUtils.showShort(data.getMessage(), data.getStatus());
            }
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }

    class HotWillCall implements DataCall<Result<HotWillBean>> {

        @Override
        public void onSuccess(Result<HotWillBean> data, Object... args) {
            if (data.getStatus().equals("0000")) {


            } else {
                ToastUtils.showShort(data.getMessage(), data.getStatus());
            }
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }
}
package com.lee.vademovies.view.fragment.home;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.lee.vademovies.util.LocationUtils;
import com.lee.vademovies.util.exception.ApiException;
import com.lee.vademovies.util.recycler.ScrollLinearLayoutManager;
import com.lee.vademovies.util.recycler.SpacesItemDecoration;
import com.lee.vademovies.view.adapter.BannerHomeAdapter;
import com.lee.vademovies.view.adapter.HotRecyclerViewAdapter;
import com.lee.vademovies.view.adapter.HotWillRecyclerViewAdapter;
import com.lee.vademovies.view.adapter.HotingRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.iv_location_home)
    ImageView mIvLocationHome;
    @BindView(R.id.tv_location_home)
    TextView mTvLocationHome;
    @BindView(R.id.rl_location_home)
    RelativeLayout mRlLocationHome;
    @BindView(R.id.iv_search_home)
    ImageView mIvSearchHome;
    @BindView(R.id.et_search_home)
    EditText mEtSearchHome;
    @BindView(R.id.tv_search_home)
    TextView mTvSearchHome;
    @BindView(R.id.rl_search_home)
    RelativeLayout mRlSearchHome;
    @BindView(R.id.tv_hot_movie_home)
    TextView mTvHotMovieHome;
    @BindView(R.id.rl_list1_home)
    RelativeLayout mRlList1Home;
    @BindView(R.id.tv_release_movie_home)
    TextView mTvReleaseMovieHome;
    @BindView(R.id.rl_list2_home)
    RelativeLayout mRlList2Home;
    @BindView(R.id.tv_will_movie_home)
    TextView mTvWillMovieHome;
    @BindView(R.id.rl_list3_home)
    RelativeLayout mRlList3Home;
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

    private boolean mRlSearchHomeStatus = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_home, null);

        unbinder = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Location location = LocationUtils.getInstance(getActivity()).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            LogUtils.d("FLY.LocationUtils", address);
            mTvLocationHome.setText(address);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mHomePresenter.unBind();
        mHomeHotingPresenter.unBind();
        mHomeHotWillPresenter.unBind();
        LocationUtils.getInstance(getActivity()).removeLocationUpdatesListener();

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

        mRv1Home.setNestedScrollingEnabled(false);
        mRv2Home.setNestedScrollingEnabled(false);
        mRv3Home.setNestedScrollingEnabled(false);

        mRv1Home.setLayoutManager(new ScrollLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRv2Home.setLayoutManager(new ScrollLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRv3Home.setLayoutManager(new ScrollLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mRv1Home.addItemDecoration(new SpacesItemDecoration(20));
        mRv2Home.addItemDecoration(new SpacesItemDecoration(20));
        mRv3Home.addItemDecoration(new SpacesItemDecoration(20));
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
        initView();

    }

    @OnClick({R.id.rl_location_home, R.id.iv_search_home, R.id.tv_search_home, R.id.rl_search_home, R.id.rcf_home, R.id.movie_text_xian, R.id.movie_text_dong, R.id.tv_hot_movie_home, R.id.view1, R.id.iv_more1_home, R.id.rv1_home, R.id.rl_list1_home, R.id.tv_release_movie_home, R.id.view2, R.id.iv_more2_home, R.id.rv2_home, R.id.rl_list2_home, R.id.tv_will_movie_home, R.id.view3, R.id.iv_more3_home, R.id.rv3_home, R.id.rl_list3_home})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_location_home:

                break;
            case R.id.iv_search_home:
                openSearch();
                break;
            case R.id.tv_search_home:
                ToastUtils.showShort(R.string.search_toast);
                mEtSearchHome.setText(null);
                closeSearch();
                break;
            case R.id.rl_search_home:
                break;
            case R.id.rcf_home:
                break;
            case R.id.movie_text_xian:
                break;
            case R.id.movie_text_dong:
                break;
            case R.id.tv_hot_movie_home:
                break;
            case R.id.view1:
                break;
            case R.id.iv_more1_home:
                break;
            case R.id.rv1_home:
                break;
            case R.id.rl_list1_home:
                break;
            case R.id.tv_release_movie_home:
                break;
            case R.id.view2:
                break;
            case R.id.iv_more2_home:
                break;
            case R.id.rv2_home:
                break;
            case R.id.rl_list2_home:
                break;
            case R.id.tv_will_movie_home:
                break;
            case R.id.view3:
                break;
            case R.id.iv_more3_home:
                break;
            case R.id.rv3_home:
                break;
            case R.id.rl_list3_home:
                break;
        }
    }

    class HomeCall implements DataCall<Result<List<HotBean.ResultBean>>> {

        @Override
        public void onSuccess(Result<List<HotBean.ResultBean>> data, Object... args) {
            if (data.getStatus().equals("0000")) {
                HotRecyclerViewAdapter hotRecyclerViewAdapter = new HotRecyclerViewAdapter(getActivity(), data);
                mRv1Home.setAdapter(hotRecyclerViewAdapter);
            }
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }

    class HomeHotingCall implements DataCall<Result<List<HotingBean.ResultBean>>> {

        @Override
        public void onSuccess(Result<List<HotingBean.ResultBean>> data, Object... args) {
            if (data.getStatus().equals("0000")) {
                HotingRecyclerViewAdapter hotingRecyclerViewAdapter = new HotingRecyclerViewAdapter(getActivity(), data);
                mRv2Home.setAdapter(hotingRecyclerViewAdapter);

            } else {
                ToastUtils.showShort(data.getMessage(), data.getStatus());
            }
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }

    class HotWillCall implements DataCall<Result<List<HotWillBean.ResultBean>>> {

        @Override
        public void onSuccess(Result<List<HotWillBean.ResultBean>> data, Object... args) {
            if (data.getStatus().equals("0000")) {
                HotWillRecyclerViewAdapter hotWillRecyclerViewAdapter = new HotWillRecyclerViewAdapter(getActivity(), data);
                mRv3Home.setAdapter(hotWillRecyclerViewAdapter);

            } else {
                ToastUtils.showShort(data.getMessage(), data.getStatus());
            }
        }

        @Override
        public void onFail(ApiException data, Object... args) {
            ToastUtils.showShort(data.getDisplayMessage());
        }
    }


    //打开搜索框
    private void openSearch() {
        if (mRlSearchHomeStatus) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRlSearchHome, "translationX", 0, (dp2px(getActivity(), -170)));
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
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRlSearchHome, "translationX", (dp2px(getActivity(), -170)), 0);
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
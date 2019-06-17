package com.lee.vademovies.view.fragment.home;

import com.lee.vademovies.R;
import com.lee.vademovies.base.BaseFragment;

/**
 * Created :  LiZhIX
 * Date :  2019/6/14 16:27
 * Description  :  主页Fragment
 */
public class MineFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if(isVisible){
            //更新界面数据，如果数据还在下载中，就显示加载框


        }else{
            //关闭加载框
            mLoadDialog.cancel();
        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }
}
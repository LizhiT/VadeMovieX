package com.lee.vademovies.presenter;

import com.lee.vademovies.base.BasePresenter;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.util.httputil.ApiService;
import com.lee.vademovies.util.httputil.NetWorkMangager;

import io.reactivex.Observable;

/**
 * Created :  LiZhIX
 * Date :  2019/6/21 9:50
 * Description  :   我的首页Presenter
 */
public class UserInfoPresenter extends BasePresenter {


    public UserInfoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = new NetWorkMangager().create(ApiService.class);
        return apiService.queryHome();

    }
}

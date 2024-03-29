package com.lee.vademovies.presenter;

import com.lee.vademovies.base.BasePresenter;
import com.lee.vademovies.util.httputil.ApiService;
import com.lee.vademovies.util.httputil.NetWorkMangager;
import com.lee.vademovies.model.DataCall;

import io.reactivex.Observable;

/**
 * Created :  LiZhIX
 * Date :  2019/6/17 14:26
 * Description  :
 */
public class HomeHotPresenter extends BasePresenter {

    public HomeHotPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NetWorkMangager.instance().create(ApiService.class);
        return apiService.getHomeData((int) args[0], (int) args[1]);
    }
}

package com.lee.vademovies.presenter;

import com.lee.vademovies.base.BasePresenter;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.util.httputil.ApiService;
import com.lee.vademovies.util.httputil.NetWorkMangager;

import io.reactivex.Observable;

/**
 * Created :  LiZhIX
 * Date :  2019/6/21 10:03
 * Description  :
 */
public class MinePresenter extends BasePresenter {

    public MinePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = new NetWorkMangager().create(ApiService.class);
        return apiService.queryHome();
    }
}

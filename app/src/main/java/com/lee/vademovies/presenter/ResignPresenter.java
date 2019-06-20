package com.lee.vademovies.presenter;

import com.lee.vademovies.base.BasePresenter;
import com.lee.vademovies.util.httputil.ApiService;
import com.lee.vademovies.util.httputil.NetWorkMangager;
import com.lee.vademovies.model.DataCall;

import io.reactivex.Observable;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 19:37
 * Description  :  注册的P层
 */
public class ResignPresenter extends BasePresenter {
    public ResignPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NetWorkMangager.instance().create(ApiService.class);
        return apiService.resigner((String) args[0],
                (String) args[1], (String) args[2],
                (String) args[3], (int) args[4],
                (String) args[5], (String) args[6],
                (String) args[7], (String) args[8],
                (String) args[9], (String) args[10]);
    }
}

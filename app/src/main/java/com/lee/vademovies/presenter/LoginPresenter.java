package com.lee.vademovies.presenter;

import com.lee.vademovies.base.BasePresenter;
import com.lee.vademovies.httputil.ApiService;
import com.lee.vademovies.httputil.NetWorkMangager;
import com.lee.vademovies.model.DataCall;

import io.reactivex.Observable;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 14:42
 * Description  :  登录P层
 */
public class LoginPresenter extends BasePresenter {

    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NetWorkMangager.instance().create(ApiService.class);
        return apiService.login((String) args[0], (String) args[1]);
    }

}

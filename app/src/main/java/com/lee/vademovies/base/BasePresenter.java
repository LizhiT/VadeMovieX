package com.lee.vademovies.base;

import com.lee.vademovies.model.bean.Result;
import com.lee.vademovies.model.DataCall;
import com.lee.vademovies.util.exception.CustomException;
import com.lee.vademovies.util.exception.ResponseTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 14:24
 * Description  :  Presenter基础类
 */
public abstract class BasePresenter {

    private DataCall dataCall;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    protected abstract Observable observable(Object... args);

    public void reqeust(Object... args) {
        observable(args)
                .compose(ResponseTransformer.handleResult())
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(Observable upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        dataCall.onSuccess(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // 处理异常
                        dataCall.onFail(CustomException.handleException(throwable));
                    }
                });

    }

    //解绑
    public void unBind() {
        dataCall = null;
    }


}

package com.lee.vademovies.model;

import com.lee.vademovies.util.exception.ApiException;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 14:26
 * Description  :  响应回执
 */
public interface DataCall<T> {
    void onSuccess(T data, Object... args);

    void onFail(ApiException data, Object... args);
}

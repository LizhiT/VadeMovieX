package com.lee.vademovies.util.permisstion;

/**
 * Created :  LiZhIX
 * Date :  2019/6/15 8:31
 * Description  :    动态权限接口
 */
public interface PermissionCallback {
    void onPermissionGranted();

    void shouldShowRational(String permisson);

    void onPermissonReject(String permisson);
}



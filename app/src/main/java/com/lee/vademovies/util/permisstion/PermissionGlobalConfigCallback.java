package com.lee.vademovies.util.permisstion;

/**
 * Created :  LiZhIX
 * Date :  2019/6/15 9:31
 * Description  :
 */
public abstract class PermissionGlobalConfigCallback {
    abstract public void shouldShowRational(String permission, int ration);

    abstract public void onPermissonReject(String permission, int reject);
}

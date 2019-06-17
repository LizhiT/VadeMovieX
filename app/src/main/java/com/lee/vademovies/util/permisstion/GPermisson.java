package com.lee.vademovies.util.permisstion;

import android.content.Context;

/**
 * Created :  LiZhIX
 * Date :  2019/6/15 8:34
 * Description  :  门面类，提供api调用
 */
public class GPermisson {
    private static PermissionGlobalConfigCallback globalConfigCallback;
    //申请回调
    private PermissionCallback callback;
    //需要申请的权限
    private String[] permissions;
    private Context context;

    public GPermisson(Context context) {
        this.context = context;
    }

    public static void init(PermissionGlobalConfigCallback callback) {
        globalConfigCallback = callback;
    }

    static PermissionGlobalConfigCallback getGlobalConfigCallback() {
        return globalConfigCallback;
    }

    public static GPermisson with(Context context) {
        GPermisson permisson = new GPermisson(context);
        return permisson;
    }

    public GPermisson permisson(String[] permissons) {
        this.permissions = permissons;
        return this;
    }

    public GPermisson callback(PermissionCallback callback) {
        this.callback = callback;
        return this;
    }

    public void request() {
        if (permissions == null || permissions.length <= 0) {
            return;
        }
        PermissionActivity.request(context, permissions, callback);
    }

    /**
     * 写一个接口，将申请被拒绝的上述两种情况交给调用者自行处理，框架内不处理
     */
    public abstract class PermissionGlobalConfigCallback {
        abstract public void shouldShowRational(String permission, int ration);

        abstract public void onPermissonReject(String permission, int reject);
    }

}

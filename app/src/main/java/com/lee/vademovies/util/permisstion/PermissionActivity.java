package com.lee.vademovies.util.permisstion;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import io.reactivex.annotations.Nullable;

/**
 * Created :  LiZhIX
 * Date :  2019/6/15 8:31
 * Description  :    动态权限申请类
 */
public class PermissionActivity extends Activity {
    public static final String KEY_PERMISSIONS = "permissions";
    private static final int RC_REQUEST_PERMISSION = 100;
    private static PermissionCallback CALLBACK;

    /*
     * 添加一个静态方法方便使用
     */
    public static void request(Context context, String[] permissions, PermissionCallback callback) {
        CALLBACK = callback;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(KEY_PERMISSIONS, permissions);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!intent.hasExtra(KEY_PERMISSIONS)) {
            return;
        }
        // 当api大于23时，才进行权限申请
        String[] permissions = getIntent().getStringArrayExtra(KEY_PERMISSIONS);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, RC_REQUEST_PERMISSION);
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != RC_REQUEST_PERMISSION) {
            return;
        }
        // 处理申请结果
        boolean[] shouldShowRequestPermissionRationale = new boolean[permissions.length];
        for (int i = 0; i < permissions.length; ++i) {
            shouldShowRequestPermissionRationale[i] = shouldShowRequestPermissionRationale(permissions[i]);
        }
        this.onRequestPermissionsResult(permissions, grantResults, shouldShowRequestPermissionRationale);
    }


    @TargetApi(23)
    void onRequestPermissionsResult(String[] permissions, int[] grantResults, boolean[] shouldShowRequestPermissionRationale) {
        int length = permissions.length;
        int granted = 0;
        for (int i = 0; i < length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale[i] == true) {
                    CALLBACK.shouldShowRational(permissions[i]);
                } else {
                    CALLBACK.onPermissonReject(permissions[i]);
                }
            } else {
                granted++;
            }
        }
        if (granted == length) {
            CALLBACK.onPermissionGranted();
        }
        finish();
    }

}

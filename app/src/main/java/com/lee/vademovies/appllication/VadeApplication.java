package com.lee.vademovies.appllication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.LogUtils;
import com.lee.vademovies.util.ScreenAdapter;
import com.lee.vademovies.util.db.DaoMaster;
import com.lee.vademovies.util.db.DaoSession;
import com.lee.vademovies.util.db.UserInfoDao;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 15:04
 * Description  :   Application
 */
public class VadeApplication extends Application {

    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;


    /**
     * context 全局唯一的上下文
     */
    private static Context context;

    private static SharedPreferences sharedPreferences;
    private UserInfoDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();

        ScreenAdapter.setup(this);
//        ScreenAdapter.register(this,);
        context = this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        sharedPreferences = getSharedPreferences("shareX", MODE_PRIVATE);

        //设置log打印开关
        LogUtils.getConfig().setLogSwitch(true);

        /**初始化数据库*/
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.
                DevOpenHelper(this, "user-db", null);

        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        DaoSession daoSession = master.newSession();
        userDao = daoSession.getUserInfoDao();

    }

    public static SharedPreferences getShare() {
        return sharedPreferences;
    }

    /**
     * @return 全局唯一的上下文
     * @author: LiZhIX
     * @describe: 获取全局Application的上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }


}

package com.lee.vademovies.util.httputil;

import com.blankj.utilcode.util.LogUtils;
import com.lee.vademovies.appllication.VadeApplication;
import com.lee.vademovies.bean.UserInfo;
import com.lee.vademovies.util.db.DaoMaster;
import com.lee.vademovies.util.db.UserInfoDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 17:04
 * Description  :  网络管理类
 */
public class NetWorkMangager {

    private static NetWorkMangager instance;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    private NetWorkMangager() {
        init(false);
    }

    public static NetWorkMangager instance() {
        if (instance == null) {
            instance = new NetWorkMangager();
        }
        return instance;
    }

    private void init(boolean userHeader) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数，请求结果

        if (userHeader) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new InsertHeader())//添加请求头
                    .build();
        } else {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build();
        }


        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl("http://172.17.8.100/movieApi/")//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();

    }

    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }


    private class InsertHeader implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取数据库
            UserInfoDao loginDao;
            List<UserInfo> list;
            loginDao = DaoMaster.newDevSession(VadeApplication.getContext(), UserInfoDao.TABLENAME).getUserInfoDao();
            list = new ArrayList<>();
            list.addAll(loginDao.loadAll());
            LogUtils.d("intercept: " + list);
            //循环判断
            for (int i = 0; i < list.size(); i++) {
                UserInfo lo = list.get(i);
                if (lo == null) {
                    return null;
                } else {
                    if (lo.getTtt() == 1) {
                        Request request = chain.request();
                        Request.Builder header = request.newBuilder()
                                .addHeader("userId", String.valueOf(list.get(i).getId()))
                                .addHeader("sessionId", list.get(i).getSessionId());
                        Request build = header.build();
                        Response response = chain.proceed(build);
                        LogUtils.d("intercept: " + list.get(i).getSessionId());
                        LogUtils.d("intercept: " + String.valueOf(list.get(i).getId()));
                        return response;
                    }
                }
            }
            return null;
        }
    }
}

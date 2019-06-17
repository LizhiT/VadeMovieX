package com.lee.vademovies.httputil;

import com.lee.vademovies.bean.LoginBean;
import com.lee.vademovies.bean.Result;
import com.lee.vademovies.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 17:04
 * Description  :  请求接口
 */
public interface ApiService {
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<UserInfo>> login(@Field("phone") String phone,
                                       @Field("pwd") String pwd);

    //注册
    @FormUrlEncoded
    @POST("user/v1/registerUser")
    Observable<Result> resigner(@Field("nickName") String nickName,     //昵称
                                @Field("phone") String phone,           //手机号
                                @Field("pwd") String pwd,               //密码
                                @Field("pwd2") String pwd2,             //重复密码
                                @Field("sex") int sex,                  //性别
                                @Field("birthday") String birthday,     //生日
                                @Field("imei") String imei,             //移动设备唯一识别码
                                @Field("ua") String ua,                 //设备类型
                                @Field("screenSize") String screenSize, //屏幕尺寸
                                @Field("os") String os,                 //手机系统
                                @Field("email") String email);          //邮箱


    //查询会员首页信息
    @GET("user/v1/verify/findUserHomeInfo")
    Observable<Result<LoginBean.ResultBean.UserInfoBean>> queryHome(@Header("userId") int userId,
                                                                    @Header("sessionId") String sessionId);

    //查询热门电影信息
    @GET("movie/v1/findHotMovieList")
    Observable<Result> getHomeData(@Query("page") int page,
                                   @Query("count") int count);

    //查询热门电影信息
    @GET("movie/v1/findReleaseMovieList")
    Observable<Result> getHomeHotingData(@Query("page") int page,
                                         @Query("count") int count);

    //查询热门电影信息
    @GET("movie/v1/findComingSoonMovieList")
    Observable<Result> getHomeWillData(@Query("page") int page,
                                       @Query("count") int count);

}

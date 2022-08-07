package com.moyang.room.okhttp;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/7 9:59
 */
public interface HttpBinService {

    @POST("/user/login")
    @FormUrlEncoded
    Flowable<Result<ResponseData>> login(@Field("username") String username, @Field("password") String password);


    @GET("get")
    Flowable<ResponseBody> getList(@Query("username") String username, @Query("password") String password, @QueryMap Map<String,String> map);

    @HTTP(method = "POST")
    @Headers({"os:android", "version:1.0"})
    Call<ResponseBody> login(@Body ResponseBody responseBody);

    @POST("/page/{id}")
    Call<ResponseBody> postInPath(@Path("id") String id, @Header("os") String os);


    @POST("post")
    Call<ResponseBody> postUrl(@Url String url);

    @GET("/lg/collect/list/{id}/json")
    Flowable<ResponseBody> getArticleList(@Path("id") int id);

    @POST("post")
    @Multipart
    Call<ResponseBody> upload(@Part MultipartBody.Part file);

    @GET
    Flowable<ResponseBody> download(@Url String url);
}

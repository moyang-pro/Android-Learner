package com.moyang.room.okhttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.moyang.room.BuildConfig;
import com.moyang.room.R;

import org.reactivestreams.Publisher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 1. 同步异步 get post请求:
 *
 * 2. 几种常见的Content-Type类型(POST提交数据的方式):
 * -- application/x-www-form-urlencoded(默认):
 *   提交的表单数据会转换为键值对并按照key1=val&key2=val2的方式进行编码,key和val都进行了URL转码
 * -- multipart/form-data:
 * -- application/json:
 * 3. okHttp配置:
 * -- 拦截器
 *
 */
public class OkhttpLearnActivity extends AppCompatActivity {

    private OkHttpClient mHttpClient;

    private Map<String, List<Cookie>> cookies = new HashMap<>();
    private Retrofit mRetrofit;
    private HttpBinService httpBinService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_learn);
        FormBody body = new FormBody.Builder().add("username", "moyang")
                .add("password", "moyang.5251").build();
        // addInterceptor 比 addNetworkInterceptor 先执行
        mHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    // 存在内存中
                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookies.put(httpUrl.host(), list);
                    }

                    @NonNull
                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> list = cookies.get(httpUrl.host());

                        return list == null ? new ArrayList<>():list;
                    }
                })
//                .cache(new Cache(new File("D:\\moyang\\code\\android\\room"), 1024 * 1024))
//                .addInterceptor(new Interceptor() {
//            @NonNull
//            @Override
//            public Response intercept(@NonNull Chain chain) throws IOException {
//                // 请求拦截
//                Request request = chain.request();
//
//                request.newBuilder().addHeader("os", "android")
//                        .addHeader("version", BuildConfig.VERSION_NAME);
//                // 响应拦截
//                Response response = chain.proceed(request);
//
//                return response;
//            }
//        })
                .build();

        String url0 = "https://www.wanandroid.com/";
        String url = "https://www.httpbin.org/";
        mRetrofit = new Retrofit.Builder().baseUrl(url)
//                .client(new OkHttpClient.Builder().cookieJar(new CookieJar() {
//                    // 存在内存中
//                    @Override
//                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
//                        cookies.put(httpUrl.host(), list);
//                    }
//
//                    @NonNull
//                    @Override
//                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
//                        List<Cookie> list = cookies.get(httpUrl.host());
//
//                        return list == null ? new ArrayList<>():list;
//                    }
//                }).build())
//                .addConverterFactory(GsonConverterFactory.create()) // 转换器
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Rxjava3 适配器
                .build();
        httpBinService = mRetrofit.create(HttpBinService.class);

        // 同步 get请求
        Button btn_sync_get = findViewById(R.id.btn_sync_get);
        btn_sync_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder().url("https://www.wanandroid.com/lg/collect/list/0/json").build();
                        // 准备好请求的call对象
                        Call call = mHttpClient.newCall(request);
                        try {
                            Response response = call.execute();
                            Log.d("moyang99", "btn_sync_get response ==== " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

        // 异步 get请求
        Button btn_async_get = findViewById(R.id.btn_async_get);
        btn_async_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder().url("https://httpbin.org/get?a=1&b=2").build();
                // 准备好请求的call对象
                Call call = mHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d("moyang99", "btn_async_get response onFailure ");
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 成功
                        if (response.isSuccessful()) {
                            Log.d("moyang99", "btn_async_get response isSuccessful ==== " + response.body().string());
                        }

                    }
                });

            }
        });

        // 同步 post请求
        Button btn_sync_post = findViewById(R.id.btn_sync_post);
        btn_sync_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
                        Request request = new Request.Builder().url("https://www.wanandroid.com/user/login").post(body).build();
                        // 准备好请求的call对象
                        Call call = mHttpClient.newCall(request);
                        try {
                            Response response = call.execute();
                            Log.d("moyang99", "btn_sync_post response ==== " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        // 异步 post请求
        Button btn_async_post = findViewById(R.id.btn_async_post);
        btn_async_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
                Request request = new Request.Builder().url("https://httpbin.org/post").post(formBody).build();
                // 准备好请求的call对象
                Call call = mHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 成功
                        if (response.isSuccessful()) {
                            Log.d("moyang99", "btn_async_post response isSuccessful ==== " + response.body().string());
                        }
                    }
                });
            }
        });


        // retrofit请求
        Button btn_async_retrofit = findViewById(R.id.btn_async_retrofit);
        btn_async_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               Flowable<Result<ResponseData>> call = httpBinService.login("moyang", "moyang.5251");
//                call.enqueue(new retrofit2.Callback<Result<ResponseData>>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<Result<ResponseData>> call, retrofit2.Response<Result<ResponseData>> response) {
//                        Log.i("moyang99", "retrofit response ==== " + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<Result<ResponseData>> call, Throwable t) {
//
//                    }
//                });

//                httpBinService.login("moyang", "moyang.5251")
//                        .flatMap(new Function<Result<ResponseData>, Publisher<ResponseBody>>() {
//                            @Override
//                            public Publisher<ResponseBody> apply(Result<ResponseData> responseDataResult) throws Throwable {
//                                return httpBinService.getArticleList(0);
//                            }
//                        })
//                        .observeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<ResponseBody>() {
//                            @Override
//                            public void accept(ResponseBody responseBody) throws Throwable {
//                                // ...
//                                Log.i("moyang99", "retrofit response ==== " + responseBody.string());
//                            }
//                        });



                Log.i("moyang99", "****************");
                new Thread() {
                    @Override
                    public void run() {
                        File file = new File("1.txt");
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        MultipartBody.Part part =MultipartBody.Part.createFormData("file",
                                "1.txt", RequestBody.create(file, MediaType.parse("text/plain")));
                        retrofit2.Call<ResponseBody> call2 = httpBinService.upload(part);
                        try {
                            Log.i("moyang99", "retrofit upload response ==== " +  call2.execute().body());
                        } catch (IOException e) {
                            Log.i("moyang99", "************ " + e);
                        }
                    }
                }.start();


            }
        });
    }
}
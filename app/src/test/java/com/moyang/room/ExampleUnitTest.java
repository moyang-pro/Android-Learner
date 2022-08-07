package com.moyang.room;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.moyang.room.okhttp.HttpBinService;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Retrofit mRetrofit;
    private HttpBinService httpBinService;
    @Test
    public void addition_isCorrect() throws IOException {
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
        File file = new File("D:\\moyang\\code\\android\\room\\app\\src\\main\\assets\\1.txt");
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
        System.out.println(call2.execute().body().string());
    }
}
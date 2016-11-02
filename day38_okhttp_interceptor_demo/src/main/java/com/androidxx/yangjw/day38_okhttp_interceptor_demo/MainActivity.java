package com.androidxx.yangjw.day38_okhttp_interceptor_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Okhttp的拦截器
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private OkHttpClient httpClient;
    private CacheControl cacheControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //参数1：缓存的目录
        //参数2：缓存空间的最大值
        Cache cache = new Cache(getCacheDir(),4*1024*1024);
        httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(cache)
                .build();

        //缓存控制器，用于请求服务器，配置缓存的参数
        cacheControl = new CacheControl.Builder()
                .maxAge(3600, TimeUnit.SECONDS) //缓存的有效期为1天
                .build();

    }

    public void click(View view) {
        Request request = new Request.Builder()
                .url("http://api.liwushuo.com/v2/channels/129/items?gender=1&limit=20&offset=0&generation=2")
                .cacheControl(cacheControl)
                .build();
        httpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Log.i(TAG, "onResponse: " + string);
                    }
                });

    }

    /**
     * 拦截器
     */
    class CacheInterceptor implements Interceptor {

        //拦截的方法（在此方法中进行拦截）
        @Override
        public Response intercept(Chain chain) throws IOException {
            //此处获取的Request对象是还没有进行网络请求的对象
            Request request = chain.request();//请求
            //执行请求，此句代码之前的内容表示是对请求进行拦截
            //在此代码之后的内容表示对结果进行的拦截
            Response response = chain.proceed(request); //执行请求，并且返回结果
            //添加缓存的有效期
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma") //Pragma是http协议中控制缓存有效期的一个属性
                    .removeHeader("Cache-Control")//Cache-Control是http协议中控制缓存有效期的一个属性
                    //缓存有效时间为一天
                    .header("Cache-Control", "max-age=" + 3600)
                    .build();
            return response1;
        }
    }
}

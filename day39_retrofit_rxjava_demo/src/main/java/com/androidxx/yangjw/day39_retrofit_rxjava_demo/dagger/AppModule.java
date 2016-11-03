package com.androidxx.yangjw.day39_retrofit_rxjava_demo.dagger;

import android.content.Context;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.MyApplication;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.model.ApiService;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter.IAppPresenter;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter.impl.AppPresenter;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangjw on 2016/11/3.
 */
@Module
public class AppModule {
//    @Provides
//    public Context provideApplication() {
//        return MyApplication.getInstance();
//    }

    @Provides
    public OkHttpClient provideOkHttp() {
//        File cacheDir = context.getCacheDir();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor()
//                .cache(new Cache(cacheDir,4*1024*1024))
                .build();
        return okHttpClient;

    }

    @Provides
    public ApiService provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .client(client) //使用自定义OkHttp客户端
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    @Provides
    public IAppPresenter provideAppPresenter(ApiService apiService) {
        return new AppPresenter(apiService);
    }
}

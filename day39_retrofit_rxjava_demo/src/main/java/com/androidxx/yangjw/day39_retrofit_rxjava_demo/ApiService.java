package com.androidxx.yangjw.day39_retrofit_rxjava_demo;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.bean.SelectBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yangjw on 2016/11/3.
 * APP的网络请求接口
 */
public interface ApiService {

    @GET("v2/channels/101/items?ad=2&gender=1&generation=2&limit=20")
    Observable<SelectBean> querySelectBean(@Query("offset") int offset);
}

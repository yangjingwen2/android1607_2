package com.androidxx.yangjw.day39_retrofit_demo;

import com.androidxx.yangjw.day39_retrofit_demo.bean.GiftBean;
import com.androidxx.yangjw.day39_retrofit_demo.bean.SelectBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yangjw on 2016/11/3.
 */
public interface ApiService {

    /**
     * get请求
     * GET参数是URI
     * @return
     */
    @GET("v2/channels/{num}/items")
    Call<SelectBean> querySelectList(@Path("num") int path,@Query("ad")int ad, @Query("gender") int gender
            , @Query("generation") int generation, @Query("limit") int limit, @Query("offset")int offset);

    /**
     * Post请求
     * @return
     */
    @FormUrlEncoded
    @POST("majax.action?method=getGiftList")
    Call<GiftBean> queryGiftList(@Field("pageno") int num);




}

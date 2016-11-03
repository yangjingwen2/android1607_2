package com.androidxx.yangjw.day39_retrofit_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.day39_retrofit_demo.bean.GiftBean;
import com.androidxx.yangjw.day39_retrofit_demo.bean.SelectBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")//域名
//                .baseUrl("http://www.1688wan.com/")
                .addConverterFactory(GsonConverterFactory.create()) //表示使用Gson解析Json为javabean对象
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public void get(View view) {
        Call<SelectBean> beanCall = apiService.querySelectList(101,2,1,2,20,0);
        beanCall.enqueue(new Callback<SelectBean>() {
            @Override
            public void onResponse(Call<SelectBean> call, Response<SelectBean> response) {
                SelectBean selectBean = response.body();
                Log.i(TAG, "onResponse: " + Thread.currentThread().getName());
                Log.i(TAG, "onResponse: " + selectBean.getData().getItems().size());
            }

            @Override
            public void onFailure(Call<SelectBean> call, Throwable t) {

            }
        });
    }

    public void post(View view) {
            apiService.queryGiftList(1).enqueue(new Callback<GiftBean>() {
                @Override
                public void onResponse(Call<GiftBean> call, Response<GiftBean> response) {
                    GiftBean giftBean = response.body();
                    Log.i(TAG, "onResponse: gift size = " + giftBean.getList().size());
                }

                @Override
                public void onFailure(Call<GiftBean> call, Throwable t) {

                }
            });
    }
}

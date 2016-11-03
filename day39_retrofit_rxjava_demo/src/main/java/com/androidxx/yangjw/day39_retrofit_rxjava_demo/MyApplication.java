package com.androidxx.yangjw.day39_retrofit_rxjava_demo;

import android.app.Application;

/**
 * Created by yangjw on 2016/11/3.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }
}

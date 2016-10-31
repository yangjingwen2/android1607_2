package com.androidxx.yangjw.day36_mvc_demo.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yangjw on 2016/10/31.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}

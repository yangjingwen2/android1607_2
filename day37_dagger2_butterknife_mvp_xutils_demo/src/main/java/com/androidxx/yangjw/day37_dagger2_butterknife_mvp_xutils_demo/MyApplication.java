package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yangjw on 2016/11/1.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //完成Xtuils的初始化
        x.Ext.init(this);
    }
}

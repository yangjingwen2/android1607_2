package com.androidxx.yangjw.day36_mvp_demo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yangjw on 2016/10/31.
 */
public class MyApplcation extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //Xutils的初始化
        x.Ext.init(this);
    }
}

package com.androidxx.yangjw.day36_mvp_demo.model;

import com.androidxx.yangjw.day36_mvp_demo.presenter.callback.Callback;

/**
 * Created by yangjw on 2016/10/31.
 */
public interface IMainModel {

    void queryList(int pageno, Callback callback);
    String getName();
}

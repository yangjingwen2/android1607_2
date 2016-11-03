package com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.view.IMainView;

/**
 * Created by yangjw on 2016/11/3.
 */
public interface IAppPresenter {

    void setView(IMainView mainView);

    void querySelectBean(int offset);
}

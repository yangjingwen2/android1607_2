package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.IGuidePresenter;

/**
 * Created by yangjw on 2016/11/1.
 * 请求数据的API接口
 */
public interface IGuideModel {

    void querySelectedList(int pageno,IGuidePresenter.Callback callback);

}

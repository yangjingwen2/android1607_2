package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.impl;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.common.URLConstant;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.IGuideModel;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.bean.SelectedBean;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.IGuidePresenter;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.tool.LogUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yangjw on 2016/11/1.
 */
public class GuideModel implements IGuideModel{

    private Gson gson;

    public GuideModel(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void querySelectedList(int pageno,final IGuidePresenter.Callback callback) {
        RequestParams requestParams = new RequestParams(URLConstant.GUIDE_SELECTED_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log(GuideModel.class,result);
                SelectedBean selectedBean = gson.fromJson(result, SelectedBean.class);
                callback.success(1,selectedBean);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}

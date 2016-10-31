package com.androidxx.yangjw.day36_mvc_demo.model;

import android.util.Log;

import com.androidxx.yangjw.day36_mvc_demo.model.bean.MainBean;
import com.androidxx.yangjw.day36_mvc_demo.model.http.URLConstants;
import com.androidxx.yangjw.day36_mvc_demo.tools.LogUtils;
import com.androidxx.yangjw.day36_mvc_demo.view.IDetailsView;
import com.androidxx.yangjw.day36_mvc_demo.view.IMainView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yangjw on 2016/10/31.
 * Model:M层，用来请求加载数据
 */
public class DataModel {
    public void queryDatas(final IMainView mainView)  {

        /**
         * 参数：网络请求的地址
         */
        RequestParams requestParams = new RequestParams(URLConstants.MAIN_LIST_URL);
        //配置参数
        requestParams.addBodyParameter("pageno","1");
        /**
         * 异步
         * 参数1：RequestParam对象，用来封装网络请求需要的参数
         * 参数2：callback，网络请求成功之后的回调接口
         */
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            //网络请求成功
            @Override
            public void onSuccess(String result) {
                LogUtils.log(DataModel.class,result);
                Gson gson = new Gson();
                MainBean mainBean = gson.fromJson(result, MainBean.class);
                //利用接口回调，将数据传递回Controller层
                mainView.refreshDatasResult(mainBean);
            }

            /**
             * 失败
             * @param ex
             * @param isOnCallback
             */
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            /**
             * 失败后被取消
             * @param cex
             */
            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            /**
             * 请求结束
             */
            @Override
            public void onFinished() {

            }
        });

    }

    public void queryDetails(String id,final IDetailsView detailsView) {
        RequestParams requestParams = new RequestParams(URLConstants.DETAILS_URL);
        requestParams.addBodyParameter("id",id);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                detailsView.success(result);
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

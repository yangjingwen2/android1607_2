package com.androidxx.yangjw.day36_mvp_demo.model;

import com.androidxx.yangjw.day36_mvp_demo.model.bean.GiftBean;
import com.androidxx.yangjw.day36_mvp_demo.model.http.URLConstant;
import com.androidxx.yangjw.day36_mvp_demo.tools.LogUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yangjw on 2016/10/31.
 */
public class MainModel implements IMainModel{

    /**
     * 请求列表数据
     * @param pageno
     */
    public void queryList(int pageno,final  com.androidxx.yangjw.day36_mvp_demo.presenter.callback.Callback callback) {
        RequestParams requestParams = new RequestParams(URLConstant.MAIN_LIST_URL);
        requestParams.addBodyParameter("pageno",pageno+"");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log(MainModel.class,result);
                Gson gson = new Gson();
                GiftBean giftBean = gson.fromJson(result, GiftBean.class);
                if (callback != null) {
                    callback.success(giftBean);
                }
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

    public String getName() {
        return "zhangsan";
    }
}

package com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter.impl;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.bean.SelectBean;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.model.ApiService;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter.IAppPresenter;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.tool.Utils;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.view.IMainView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yangjw on 2016/11/3.
 */
public class AppPresenter implements IAppPresenter {


    private static final String TAG = "androidxx";
    private ApiService apiService;
    private IMainView mainView;

    public AppPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void setView(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void querySelectBean(int offset) {
        final Map<String,List<SelectBean.DataBean.ItemsBean>> map = new ArrayMap<>();
        final List<String> dateList = new ArrayList<>();
        apiService.querySelectBean(0)
                .flatMap(new Func1<SelectBean, Observable<SelectBean.DataBean.ItemsBean>>() {
                    @Override
                    public Observable<SelectBean.DataBean.ItemsBean> call(SelectBean selectBean) {
                        List<SelectBean.DataBean.ItemsBean> items = selectBean.getData().getItems();
                        return Observable.from(items);
                    }
                })
                .map(new Func1<SelectBean.DataBean.ItemsBean, Map<String,List<SelectBean.DataBean.ItemsBean>>>() {
                    @Override
                    public Map<String, List<SelectBean.DataBean.ItemsBean>> call(SelectBean.DataBean.ItemsBean itemsBean) {
                        int publishedAt = itemsBean.getPublished_at();
                        String formatDate = Utils.formatDate(publishedAt * 1000);
                        List<SelectBean.DataBean.ItemsBean> itemsBeen = map.get(formatDate);
                        if (itemsBeen == null) {
                            itemsBeen = new ArrayList<SelectBean.DataBean.ItemsBean>();
                            dateList.add(formatDate);
                            map.put(formatDate,itemsBeen);
                        }
                        itemsBeen.add(itemsBean);

                        return map;
                    }
                })
                .subscribeOn(Schedulers.newThread()) //表示上方都执行在子线程中
                .observeOn(AndroidSchedulers.mainThread())//下方都执行在主线程中
                .subscribe(new Action1<Map<String, List<SelectBean.DataBean.ItemsBean>>>() {
                    @Override
                    public void call(Map<String, List<SelectBean.DataBean.ItemsBean>> stringListMap) {
//                        mainSelectAdapter.notifyDataSetChanged();
                        Log.i(TAG, "call: ---- ");
                        mainView.refreshAdapter(dateList,map);
                    }
                });
    }
}

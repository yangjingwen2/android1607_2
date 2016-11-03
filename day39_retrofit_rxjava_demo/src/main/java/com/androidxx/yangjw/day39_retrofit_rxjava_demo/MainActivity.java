package com.androidxx.yangjw.day39_retrofit_rxjava_demo;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.adapter.MainSelectAdapter;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.bean.SelectBean;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.dagger.DaggerAppComponent;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.model.ApiService;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter.IAppPresenter;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.presenter.impl.AppPresenter;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.view.IMainView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements IMainView{

    private static final String TAG = "androidxx";
    private Map<String,List<SelectBean.DataBean.ItemsBean>> map = new ArrayMap<>();
    private List<String> dateList = new ArrayList<>();
    private MainSelectAdapter mainSelectAdapter;

    @Inject
    IAppPresenter appPresenter;
    @BindView(R.id.main_expandable_listview)
    ExpandableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerAppComponent.create().inject(this);
        initAdapter();
        appPresenter.querySelectBean(0);
        appPresenter.setView(this);


    }


    private void initAdapter() {
        mainSelectAdapter = new MainSelectAdapter(this,dateList,map);
        mListView.setAdapter(mainSelectAdapter);
    }

    @Override
    public void refreshAdapter(List<String> groupList, Map<String, List<SelectBean.DataBean.ItemsBean>> map) {
        dateList.clear();
        this.map.clear();
        dateList.addAll(groupList);
        this.map.putAll(map);
        mainSelectAdapter.notifyDataSetChanged();
    }


//    private void loadDatas() {
//        if (apiService == null) {
//            return;
//        }
//
//        apiService.querySelectBean(0)
//                .flatMap(new Func1<SelectBean, Observable<SelectBean.DataBean.ItemsBean>>() {
//                    @Override
//                    public Observable<SelectBean.DataBean.ItemsBean> call(SelectBean selectBean) {
//                        List<SelectBean.DataBean.ItemsBean> items = selectBean.getData().getItems();
//                        return Observable.from(items);
//                    }
//                })
//                .map(new Func1<SelectBean.DataBean.ItemsBean, Map<String,List<SelectBean.DataBean.ItemsBean>>>() {
//                    @Override
//                    public Map<String, List<SelectBean.DataBean.ItemsBean>> call(SelectBean.DataBean.ItemsBean itemsBean) {
//                        int publishedAt = itemsBean.getPublished_at();
//                        String formatDate = formatDate(publishedAt * 1000);
//                        List<SelectBean.DataBean.ItemsBean> itemsBeen = map.get(formatDate);
//                        if (itemsBeen == null) {
//                            itemsBeen = new ArrayList<SelectBean.DataBean.ItemsBean>();
//                            dateList.add(formatDate);
//                            map.put(formatDate,itemsBeen);
//                        }
//                        Log.i(TAG, "call: ");
//                        itemsBeen.add(itemsBean);
//
//                        return map;
//                    }
//                })
//                .subscribeOn(Schedulers.newThread()) //表示上方都执行在子线程中
//                .observeOn(AndroidSchedulers.mainThread())//下方都执行在主线程中
//                .subscribe(new Action1<Map<String, List<SelectBean.DataBean.ItemsBean>>>() {
//                    @Override
//                    public void call(Map<String, List<SelectBean.DataBean.ItemsBean>> stringListMap) {
////                        mainSelectAdapter.notifyDataSetChanged();
//                        initAdapter();
//                    }
//                });
//
//    }


}

package com.androidxx.yangjw.day39_retrofit_rxjava_demo;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.adapter.MainSelectAdapter;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.bean.SelectBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private ApiService apiService;
    private Map<String,List<SelectBean.DataBean.ItemsBean>> map = new ArrayMap<>();
    private List<String> dateList = new ArrayList<>();

    @BindView(R.id.main_expandable_listview)
    ExpandableListView mListView;
    private MainSelectAdapter mainSelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRetrofit();
        loadDatas();

    }

    private void initAdapter() {
        mainSelectAdapter = new MainSelectAdapter(this,dateList,map);
        mListView.setAdapter(mainSelectAdapter);
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private void loadDatas() {
        if (apiService == null) {
            return;
        }

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
                        String formatDate = formatDate(publishedAt * 1000);
                        List<SelectBean.DataBean.ItemsBean> itemsBeen = map.get(formatDate);
                        if (itemsBeen == null) {
                            itemsBeen = new ArrayList<SelectBean.DataBean.ItemsBean>();
                            dateList.add(formatDate);
                            map.put(formatDate,itemsBeen);
                        }
                        Log.i(TAG, "call: ");
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
                        initAdapter();
                    }
                });

    }

    private String formatDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 EE");
        return simpleDateFormat.format(new Date(time));
    }
}

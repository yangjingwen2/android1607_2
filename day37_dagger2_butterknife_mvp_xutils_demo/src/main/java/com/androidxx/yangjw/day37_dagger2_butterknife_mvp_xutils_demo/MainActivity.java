package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.dagger.DaggerAppComponent;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.bean.SelectedBean;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.IGuidePresenter;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.impl.GuidePresenter;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.tool.LogUtils;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.ui.adapter.SelectedAdapter;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.view.ISelectedView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ISelectedView{

    @BindView(R.id.guide_selected_list_lv)
    ListView mListView;
    @Inject
    IGuidePresenter mGuidePresenter;

    private List<SelectedBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();
    private SelectedAdapter selectedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerAppComponent.create().inject(this);
        mGuidePresenter.setSelectedView(this);
        mGuidePresenter.querySelectedList(1);
        selectedAdapter = new SelectedAdapter(itemsBeanList,this);
        mListView.setAdapter(selectedAdapter);
    }

    @Override
    public void setListDatas(SelectedBean selectedBean) {
        //接收Presenter返回的数据
        if (selectedBean != null) {
            LogUtils.log(MainActivity.class,""+selectedBean.getCode());
            if (selectedBean.getData() != null) {
                itemsBeanList.addAll(selectedBean.getData().getItems());
                selectedAdapter.notifyDataSetChanged();
            }
        }
    }
}

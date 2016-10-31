package com.androidxx.yangjw.day36_mvp_demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidxx.yangjw.day36_mvp_demo.R;
import com.androidxx.yangjw.day36_mvp_demo.model.bean.GiftBean;
import com.androidxx.yangjw.day36_mvp_demo.presenter.MainPresenter;
import com.androidxx.yangjw.day36_mvp_demo.tools.LogUtils;
import com.androidxx.yangjw.day36_mvp_demo.ui.view.IMainView;

/**
 * MVP：View，activity+布局资源文件
 */
public class MainActivity extends AppCompatActivity implements IMainView{

    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        //加载数据
        mainPresenter.queryList(1);
    }

    @Override
    public void setResult(GiftBean giftBean) {
        LogUtils.log(MainActivity.class,giftBean.getList().get(0).getGiftname());
    }
}

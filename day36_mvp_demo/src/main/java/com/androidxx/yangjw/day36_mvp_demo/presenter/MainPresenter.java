package com.androidxx.yangjw.day36_mvp_demo.presenter;

import android.content.Context;

import com.androidxx.yangjw.day36_mvp_demo.model.MainModel;
import com.androidxx.yangjw.day36_mvp_demo.model.bean.GiftBean;
import com.androidxx.yangjw.day36_mvp_demo.presenter.callback.Callback;
import com.androidxx.yangjw.day36_mvp_demo.ui.view.IMainView;

/**
 * Created by yangjw on 2016/10/31.
 */
public class MainPresenter implements Callback{

    private MainModel mainModel;
    private IMainView mainView;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModel();
    }

    public void queryList(int pageno) {
        this.mainModel.queryList(pageno,this);
    }

    @Override
    public void success(GiftBean giftBean) {
        String name = this.mainModel.getName();
        if (name != null) {
            this.mainView.setResult(giftBean);
        }
    }

//    public interface Callback{
//        void success(GiftBean giftBean);
//    }
}

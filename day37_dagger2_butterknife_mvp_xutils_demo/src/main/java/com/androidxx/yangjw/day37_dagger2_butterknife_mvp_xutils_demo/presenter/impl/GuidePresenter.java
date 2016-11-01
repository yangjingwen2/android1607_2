package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.impl;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.IGuideModel;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.bean.SelectedBean;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.impl.GuideModel;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.IGuidePresenter;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.view.ISelectedView;

/**
 * Created by yangjw on 2016/11/1.
 */
public class GuidePresenter implements IGuidePresenter,IGuidePresenter.Callback{

    private IGuideModel mGuideModel;
    private ISelectedView selectedView;

    public GuidePresenter(IGuideModel guideModel) {
        this.mGuideModel = guideModel;
    }

    @Override
    public void setSelectedView(ISelectedView selectedView) {
        this.selectedView = selectedView;
    }

    @Override
    public void querySelectedList(int pageno) {
        this.mGuideModel.querySelectedList(pageno,this);
    }

    @Override
    public void success(int responseCode, SelectedBean selectedBean) {
        //获取Model的结果
        this.selectedView.setListDatas(selectedBean);
    }
}

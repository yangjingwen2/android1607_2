package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.bean.SelectedBean;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.view.ISelectedView;

/**
 * Created by yangjw on 2016/11/1.
 */
public interface IGuidePresenter {

    void setSelectedView(ISelectedView selectedView);
    /**
     * 加载指南精选列表数据
     * @param pageno
     */
    void querySelectedList(int pageno);

    interface Callback {
        void success(int responseCode, SelectedBean selectedBean);
    }
}

package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.view;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.bean.SelectedBean;

/**
 * Created by yangjw on 2016/11/1.
 */
public interface ISelectedView {

    /**
     * 接收Presenter层返回的结果
     * @param selectedBean
     */
    void setListDatas(SelectedBean selectedBean);
}

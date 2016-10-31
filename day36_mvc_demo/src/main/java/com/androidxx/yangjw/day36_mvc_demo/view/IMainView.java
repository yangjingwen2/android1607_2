package com.androidxx.yangjw.day36_mvc_demo.view;

import com.androidxx.yangjw.day36_mvc_demo.model.bean.MainBean;

/**
 * Created by yangjw on 2016/10/31.
 * 目的：通过此类完成接口回调，将Model层的数据传递给Controller层
 */
public interface IMainView {

    /**
     * 返回首页数据加载完成之后的结果回调
     * @param mainBean
     */
    void refreshDatasResult(MainBean mainBean);
}

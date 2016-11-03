package com.androidxx.yangjw.day39_retrofit_rxjava_demo.view;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.bean.SelectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by yangjw on 2016/11/3.
 */
public interface IMainView {

    void refreshAdapter(List<String> groupList, Map<String,List<SelectBean.DataBean.ItemsBean>> map);
}

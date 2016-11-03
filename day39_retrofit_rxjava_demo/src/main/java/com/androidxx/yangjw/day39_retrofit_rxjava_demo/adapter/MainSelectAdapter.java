package com.androidxx.yangjw.day39_retrofit_rxjava_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.R;
import com.androidxx.yangjw.day39_retrofit_rxjava_demo.bean.SelectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by yangjw on 2016/11/3.
 */
public class MainSelectAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> dateList;
    private Map<String,List<SelectBean.DataBean.ItemsBean>> map;

    public MainSelectAdapter(Context context, List<String> dateList, Map<String, List<SelectBean.DataBean.ItemsBean>> map) {
        this.context = context;
        this.dateList = dateList;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return dateList == null ? 0 : dateList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String date = dateList.get(groupPosition);
        List<SelectBean.DataBean.ItemsBean> itemsBeanList = map.get(date);
        return itemsBeanList == null ? 0 : itemsBeanList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView  view = (TextView) convertView;
        if (view == null) {
            view = new TextView(context);
        }
        String date = dateList.get(groupPosition);
        view.setText(date);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ImageView view = (ImageView) convertView;
        if (view == null) {
            view = new ImageView(context);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        String key = dateList.get(groupPosition);
        SelectBean.DataBean.ItemsBean itemsBean = map.get(key).get(childPosition);
        view.setImageResource(R.mipmap.ic_launcher);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

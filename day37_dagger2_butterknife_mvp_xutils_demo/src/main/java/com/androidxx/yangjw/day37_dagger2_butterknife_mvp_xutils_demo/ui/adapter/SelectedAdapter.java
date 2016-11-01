package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.R;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.bean.SelectedBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjw on 2016/11/1.
 */
public class SelectedAdapter extends BaseAdapter {
    private List<SelectedBean.DataBean.ItemsBean> beanList ;
    private Context mContext;
    private LayoutInflater mLayoutInfalter;

    public SelectedAdapter(List<SelectedBean.DataBean.ItemsBean> beanList, Context mContext) {
        this.beanList = beanList;
        this.mContext = mContext;
        mLayoutInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = mLayoutInfalter.inflate(R.layout.guide_selected_item_view,parent,false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SelectedBean.DataBean.ItemsBean itemsBean = beanList.get(position);
        if (itemsBean != null) {
            String title = itemsBean.getTitle();
            viewHolder.mTitleTxt.setText(title);
            Picasso.with(mContext).load(itemsBean.getCover_image_url()).into(viewHolder.mImageView);
        }

        return view;
    }

    class ViewHolder {
        @BindView(R.id.guide_selected_item_iv)
        ImageView mImageView;
        @BindView(R.id.guide_selected_item_title_txt)
        TextView mTitleTxt;
        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}

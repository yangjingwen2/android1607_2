package com.androidxx.yangjw.day37_butterknife_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidxx.yangjw.day37_butterknife_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjw on 2016/11/1.
 */
public class HomeListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public HomeListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 20;
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
            view = layoutInflater.inflate(R.layout.home_list_item_view,parent,false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mShowTxt.setText("ITEM" + position);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.home_list_item_txt)
        TextView mShowTxt;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}

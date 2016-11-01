package com.androidxx.yangjw.day37_butterknife_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjw on 2016/11/1.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.home_fragment_show_txt)
    TextView mShowTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_view, container, false);
        ButterKnife.bind(this,view);
        mShowTxt.setText("这是一个Fragment");
        return view;
    }

    public void setmShowTxt(String content) {
        mShowTxt.setText("这是Activity传入的数据：" + content);
    }


}

package com.androidxx.yangjw.day36_depend_inject_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangjw on 2016/11/1.
 */
public class MyFragment extends Fragment {

    @BindView(R.id.fragment_show_txt)
    TextView mShowTxt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        Binder.bind(this,view);
        mShowTxt.setText("这是Fragment");
        return view;
    }
}

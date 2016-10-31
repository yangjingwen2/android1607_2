package com.androidxx.yangjw.day36_depend_inject_demo;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 依赖注入
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_show_txt1)
    TextView mShowTxt1;
    @BindView(R.id.main_show_txt2)
    TextView mShowTxt2;
    @BindView(R.id.main_show_txt3)
    TextView mShowTxt3;
    @BindView(R.id.main_show_txt4)
    TextView mShowTxt4;
    @BindView(R.id.main_show_txt5)
    TextView mShowTxt5;
    @BindView(R.id.main_show_txt6)
    TextView mShowTxt6;
    @BindView(R.id.main_list_view)
    ListView mListView;

    @BindString(R.string.app_name)
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binder.bind(this);
        mShowTxt1.setText("这是一个依赖注入的案例");
        mShowTxt6.setText(name);
    }
}

package com.androidxx.yangjw.day37_dagger_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

/**
 * Dagger 2:一个依赖注入框架。主要用于给一般的java对象进行注入
 */
public class MainActivity extends AppCompatActivity {

    //需要依赖注入的对象
    @Inject
    User user;
    private TextView mShowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用Dagger开始依赖注入
        DaggerAppComponent.create().inject(this);
        String name = user.getName();

        mShowText = (TextView) findViewById(R.id.main_show_txt);
        mShowText.setText(name);
    }
}

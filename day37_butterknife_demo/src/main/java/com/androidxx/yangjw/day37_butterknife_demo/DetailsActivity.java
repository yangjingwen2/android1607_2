package com.androidxx.yangjw.day37_butterknife_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private HomeFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        int position = getIntent().getIntExtra("position", 0);
        mFragmentManager = getSupportFragmentManager();
        fragment = (HomeFragment) mFragmentManager.findFragmentById(R.id.details_fragment_content);
        fragment.setmShowTxt(String.valueOf(position));
    }
}

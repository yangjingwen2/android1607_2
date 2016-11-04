package com.androidxx.yangjw.day40_video_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.vov.vitamio.widget.VideoView;

/**
 * 介绍vitamio的使用
 */
public class VitamioActivity extends AppCompatActivity {

    private VideoView mVitamioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_vitamio);
        mVitamioView = (VideoView) findViewById(R.id.vitamio_video_view);
//        mVitamioView.setVideoPath("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        mVitamioView.setVideoPath("http://ipadlive.cntv.soooner.com/cctv_p2p_hdcctv6.m3u8");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mVitamioView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVitamioView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVitamioView.pause();
    }
}

package com.androidxx.yangjw.day40_video_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * VideoView的基本使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.main_video_view:
                intent.setClass(this,VideoViewActivity.class);
                break;
            case R.id.main_surface_view_btn:
                intent.setClass(this,SurfaceViewActivity.class);
                break;
            case R.id.main_surface_media_btn:
                intent.setClass(this,MediaPlayerSurfaceViewActivity.class);
                break;
        }
        startActivity(intent);
    }
}

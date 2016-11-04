package com.androidxx.yangjw.day40_video_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * VideoView的使用
 */
public class VideoViewActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setVideoPath("http://mvvideo1.meitudata.com/57bfff5c69171354.mp4");
        //给VideoView加上控制条
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mVideoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }


}

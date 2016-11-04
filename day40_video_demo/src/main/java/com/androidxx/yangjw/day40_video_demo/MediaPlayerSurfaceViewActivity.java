package com.androidxx.yangjw.day40_video_demo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * 通过MediaPlayer加载的媒体数据显示到SurfaceView中
 */
public class MediaPlayerSurfaceViewActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    private SurfaceView mSurfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder holder;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_surface_view);
        mSurfaceView = (SurfaceView) findViewById(R.id.media_surface_view);
        mSeekBar = (SeekBar) findViewById(R.id.media_controller_sb);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();
        initSurface();
        initListener();
    }

    private void initListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 当进度发生变化时
             * @param seekBar
             * @param progress
             * @param fromUser
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            /**
             * 触摸这个进度条时
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            /**
             * 手指离开进度条时
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });


    }

    private void initSurface() {
        try {
            mediaPlayer.setDataSource("http://mvvideo1.meitudata.com/57bfff5c69171354.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder = mSurfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //将SurfaceHolder派给MediaPlayer
                mediaPlayer.setDisplay(holder);
                //多媒体开始准备加载数据进行播放，异步
                mediaPlayer.prepareAsync();
                //监听，当视频数据准备完成
                mediaPlayer.setOnPreparedListener(MediaPlayerSurfaceViewActivity.this);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //当SurfaceView销毁，则视频播放停止
                mediaPlayer.stop();
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        int duration = mp.getDuration();
        mSeekBar.setMax(duration);
        //播放
        mp.start();
    }
}

package com.androidxx.yangjw.day40_video_demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * SurfaceView的使用
 * 步骤
 * 1、初始化SurfaceView对象
 * 2、
 */
public class SurfaceViewActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private SurfaceHolder holder;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        //创建画笔
        mPaint = new Paint();
        //设置画笔的颜色
        mPaint.setColor(Color.GREEN);
        //SurfaceView绘图的辅助类
        holder = mSurfaceView.getHolder();
        //以上是去获取holder，但是当holder初始化完成，会回调如下接口
        holder.addCallback(new SurfaceHolder.Callback() {
            /**
             * SurfaceView准备完成
             * @param holder
             */
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //使用SurfaceHolder完成绘图的任务
                        //锁定画布
                        Canvas canvas = holder.lockCanvas();
                        mPaint.setAntiAlias(true);
                        //画圆
                        canvas.drawCircle(200,200,150,mPaint);
                        //解锁画布并且将画布提交
                        holder.unlockCanvasAndPost(canvas);
                    }
                }).start();

            }

            /**
             * SurfaceView发生变化时
             * @param holder
             * @param format
             * @param width
             * @param height
             */
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            /**
             * SurfaceView销毁时执行的方法
             * @param holder
             */
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
}

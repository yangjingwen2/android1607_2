package com.androidxx.yangjw.day38_okhttp_get_post_demo.http;

import android.os.Handler;
import android.os.Message;

import com.androidxx.yangjw.day38_okhttp_get_post_demo.ICallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangjw on 2016/11/2.
 */
public class OkHttpTool {

    //每个工程中建议只有一个OkHttpClient对象，也就是说OkHttpClient对象需要时单利的。
    private static OkHttpClient okHttpClient;
    public static final int OKHTTP_SUCCESS = 1;
    public static final int OKHTTP_FAIL = 0;

    public static OkHttpHelper init(String url) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return new OkHttpHelper(url);
    }

    public static class OkHttpHelper {

        private String path;
        private ICallback callback;
        private Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (callback == null) {
                    return;
                }
                switch (msg.what) {
                    case OKHTTP_SUCCESS:
                        callback.success(msg.obj.toString());
                        break;
                    case OKHTTP_FAIL:
                        callback.failure(msg.obj.toString());
                        break;
                }

            }
        };

        public OkHttpHelper(String path) {
            this.path = path;
        }

        public OkHttpHelper post(FormBody formBody) {
            Request request = new Request.Builder()
                    .url(path)
                    .post(formBody)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //运行在子线程中
//                    callback.failure(e.getMessage());
                    Message message = mHandler.obtainMessage();
                    message.obj = e.getMessage();
                    message.what = OKHTTP_FAIL;
                    mHandler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //运行在子线程
                    Message message = mHandler.obtainMessage();
                    message.obj = response.body().string();
                    message.what = OKHTTP_SUCCESS;
                    mHandler.sendMessage(message);
                }
            });

            return this;
        }


        public void callback(ICallback callback) {
            this.callback = callback;
        }

        public OkHttpHelper get() {
            Request request = new Request.Builder()
                    .url(path)
                    .get()
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //运行在子线程中
//                    callback.failure(e.getMessage());
                    Message message = mHandler.obtainMessage();
                    message.obj = e.getMessage();
                    message.what = OKHTTP_FAIL;
                    mHandler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //运行在子线程
                    Message message = mHandler.obtainMessage();
                    message.obj = response.body().string();
                    message.what = OKHTTP_SUCCESS;
                    mHandler.sendMessage(message);
                }
            });

            return this;
        }

    }
}

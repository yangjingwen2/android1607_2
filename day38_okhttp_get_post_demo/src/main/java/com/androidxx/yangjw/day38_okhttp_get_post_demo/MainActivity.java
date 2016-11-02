package com.androidxx.yangjw.day38_okhttp_get_post_demo;

import android.app.Dialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.day38_okhttp_get_post_demo.http.OkHttpTool;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp的基本使用之Get/Post请求
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private OkHttpClient okHttpClient;
    public static final String GET_URL = "http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0";
    public static final String POST_URL = "http://www.1688wan.com/majax.action?method=getGiftList";

    private ConnectivityManager connectivityManager;
    private Call call;
    private Dialog dialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建OkHttp对象
        okHttpClient = new OkHttpClient();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void get(View view) {
        if (!networkIsAvailable()) {
            //提示网络请求不可用
            showDialog("网络不可用");
            return;
        }
        //创建Request访问对象
        Request request = new Request.Builder()
                .get() //表示是一个get请求
                .url(GET_URL) //请求地址
                .build(); //创建一个Request对象
        //执行Request请求
        call = okHttpClient.newCall(request);
//        call.execute()  //execute方法是一个同步方法，不能直接在主线程中执行
        //enqueue是异步请求，可以在主线程中执行
        call.enqueue(new Callback() {
            //访问失败（网络不可用，或者后台服务器出现故障等）
            @Override
            public void onFailure(Call call, IOException e) {
                //如果请求失败，需要使用Dialog进行提示
//                showDialog(e.getMessage());
                showDialog("网络请求失败，请检查网络后重试");
            }

            /**
             * 访问成功
             * @param call 请求对象，就是上面发起请求call对象
             * @param response 响应对象（结果对象），服务器返回的数据就在response中
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取请求到的结果
                // response.body().string()获取结果的此句代码，只能获取一次，获取一次之后会清空结果数据
                String result = response.body().string();
                if (result == null || result.isEmpty()) {
                    showDialog("没有数据了");
                } else {
                    Log.i(TAG, "onResponse: result = " + result);
                    //当结果获取完成，需要将本次请求关闭（将流关闭）
                    response.body().close();
                }

            }
        });
    }

    public void post(View view) {
        //创建一个RequestBody对象，是需要传递到后台的参数
        //FormBody是RequestBody的一个子类
        FormBody formBody = new FormBody.Builder()
                .add("pageno", "1")
                .build();
        //创建一个Request对象
        Request request = new Request.Builder()
                .url(POST_URL) //地址
                .post(formBody) //表示Post请求，参数是需要传递到后台的参数
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {

            }

            //成功
            //运行在子线程中，不能直接进行UI更新
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取请求到的结果
                String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                String name = Thread.currentThread().getName();
                Log.i(TAG, "onResponse: " + name);
            }
        });
    }

    public void tool(View view) {
        OkHttpTool.init(GET_URL).get().callback(new ICallback() {
            @Override
            public void success(String result) {
                String name = Thread.currentThread().getName();

                Log.i(TAG, name + "--success: " + result);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();//当activity销毁的时候，取消本次网络请求
        }
    }

    /**
     * 判断当前网络是否可用
     *
     * @return
     */
    private boolean networkIsAvailable() {
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    /**
     * 显示提示信息
     *
     * @param msg
     */
    private void showDialog(String msg) {
        if (dialog == null) {
            dialog = new AlertDialog
                    .Builder(this)
                    .setTitle("温馨提示")
                    .setMessage(msg)
                    .setPositiveButton("知道了", null)
                    .create();
        }
        dialog.show();
    }


}

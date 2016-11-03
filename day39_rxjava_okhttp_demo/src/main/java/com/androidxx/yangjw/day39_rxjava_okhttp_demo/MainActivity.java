package com.androidxx.yangjw.day39_rxjava_okhttp_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 完成RxJava和OkHttp的组合案例
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private OkHttpClient okHttpClient;
    public static final String GET_URL = "http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0";

    @BindView(R.id.main_show_txt)
    TextView mShowTxt;
    @BindView(R.id.main_show2_txt)
    TextView mShowTxt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(GET_URL)
                .build();

        Observable.just(request)
                //运行在子线程中
                .subscribeOn(Schedulers.newThread()) //表示被观察者运行在新的线程中
                .map(new Func1<Request, String>() {
                    @Override
                    public String call(Request request) {
                        String string = "";
                        try {
                            Log.i(TAG, "1---call: " + Thread.currentThread().getName());
                            //同步OkHttp请求
                            Response response = okHttpClient.newCall(request).execute();
                            string = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return string;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                //运行在主线程中
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        mShowTxt2.setText(s);
                        return s;
                    }
                })
                //运行在子线程
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, SimpleBean>() {
                    @Override
                    public SimpleBean call(String s) {
                        SimpleBean simpleBean = null;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int code = jsonObject.getInt("code");
                            Thread.sleep(5000);
                            simpleBean = new SimpleBean(code + "");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        return simpleBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //观察者运行在主线程中
                //运行在主线程中
                .subscribe(new Action1<SimpleBean>() {
                    @Override
                    public void call(SimpleBean simpleBean) {
                        mShowTxt.setText(simpleBean.getName());
                    }
                });


    }



}

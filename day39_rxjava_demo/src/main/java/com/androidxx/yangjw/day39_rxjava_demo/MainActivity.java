package com.androidxx.yangjw.day39_rxjava_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava的基本使用
 * 采用观察者模式设计的
 * 步骤：
 * 1、创建一个观察者
 * 2、创建一个被观察者
 * 3、订阅（监视）
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void click(View view) {
//        base1();
//        action1();
//        from();
//        map();
        flatMap();
    }

    /**
     * RxJava中FlatMap方法是一个特殊的map，也是类似一个拦截器。
     * flatmap的特点是可以返回一个Observable对象，可以再次对数据进行遍历
     */
    private void flatMap() {
        String[] strs = {"z,h,a,n,g,s,a,n,1"};
        Observable.from(strs)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        String[] split = s.split(",");
                        return Observable.from(split);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "call: " + s);
                    }
                });

    }

    /**
     * RxJava中的map方法的使用
     * Func1和Action1，类似一个观察者对象。但是Func1不是数据终点，有入口和出口；但是Action1只有入口，没有出口。
     * 一般情况下Func1是和map方法配合使用的，Action1是和subscribe方法配合使用的。
     */
    private void map() {
        String[] strs = {"zhangsan1","zhangsan2","zhangsan3","zhangsan4"};
        Observable.from(strs)
                //类似过滤器
                .map(new Func1<String, Integer>() {
                    /**
                     *
                     * @param s 就是from方法遍历的字符串
                     * @return
                     */
                    @Override
                    public Integer call(String s) {
                        String num = s.substring(s.length() - 1);
                        return Integer.parseInt(num);
                    }
                })
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 5;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "map-->call: " + integer);
                    }
                });
    }

    /**
     * RxJava中的from方法的使用
     * from方法：完成数组或者集合的迭代
     */
    private void from() {
        String[] strs = {"zhangsan1","zhangsan2","zhangsan3","zhangsan4"};
        Observable.from(strs)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "from-->call: " + s);
                    }
                });
    }

    /**
     * RxJava中使用Action1类
     * 将观察者中的部分方法缺省
     */
    private void action1() {
        final String[] strs = {"zhangsan1","zhangsan2","zhangsan3","zhangsan4"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < strs.length; i++) {
                    subscriber.onNext(strs[i]);
                }

            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "观察者: " + s);
            }
        });
    }


    private void base1() {
        //观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        };

        //被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //被观察者通知观察者
                subscriber.onNext("zhangsan");
                subscriber.onNext("lisi");
                subscriber.onNext("wangwu");
                subscriber.onCompleted();
            }
        });

        //订阅
        observable.subscribe(observer);

    }

    /**
     * 基本使用
     */
    private void base2() {
        //观察者
//        Observer observer = new Observer<String>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.i(TAG, "onSubscribe: " + d.isDisposed());
//            }
//
//            /**
//             * 接收信息
//             * @param value
//             */
//            @Override
//            public void onNext(String value) {
//                Log.i(TAG, "onNext: " + value);
//            }
//
//            /**
//             * 监听中出现错误，执行此方法
//             * @param e
//             */
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError: " + e.getMessage());
//            }
//
//            /**
//             * 监听过程结束（终止）
//             */
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete: ");
//            }
//        };
//
//        /**
//         * 被观察者
//         */
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Log.i(TAG, "subscribe: ");
//                //被观察者主动触发观察者中的方法
//                e.onNext("zhangsan");
//                e.onNext("lisi");
//                e.onNext("wangwu");
//                e.onComplete();
//            }
//        });
//
//        /**
//         * 监视（订阅）
//         */
//        observable.subscribe(observer);
    }
}

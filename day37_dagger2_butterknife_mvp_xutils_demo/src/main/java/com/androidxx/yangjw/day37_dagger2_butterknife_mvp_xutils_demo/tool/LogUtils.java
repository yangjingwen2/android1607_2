package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.tool;

import android.util.Log;

/**
 * Created by yangjw on 2016/11/1.
 */
public class LogUtils {

    private static final String TAG = "androidxx-mvp";

    public static void log(Class clazz,String log) {
        Log.i(TAG, clazz.getSimpleName() + " --> " + log);
    }
}

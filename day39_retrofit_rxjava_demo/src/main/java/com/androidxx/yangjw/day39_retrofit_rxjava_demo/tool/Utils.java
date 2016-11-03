package com.androidxx.yangjw.day39_retrofit_rxjava_demo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangjw on 2016/11/3.
 */
public class Utils {

    public static String formatDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 EE");
        return simpleDateFormat.format(new Date(time));
    }
}

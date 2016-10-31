package com.androidxx.yangjw.day36_depend_inject_demo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by yangjw on 2016/10/31.
 */
public class Binder {

    public static void bind(Activity activity) {
        Class<? extends Activity> activityClass = activity.getClass();
        //获取类中的所有属性
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            //获取属性（Field）对象
            Field declaredField = declaredFields[i];
            //获取属性上有BindView的注解
            BindView annotation = declaredField.getAnnotation(BindView.class);
            BindString bindString = declaredField.getAnnotation(BindString.class);
            if (annotation != null) {
                //获取注解中的value值
                int id = annotation.value();
                View view = activity.findViewById(id);
                //给当前Field属性，赋值view对象
                try {
                    declaredField.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (bindString != null) {
                int id = bindString.value();
                String string = activity.getResources().getString(id);
                try {
                    declaredField.set(activity,string);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

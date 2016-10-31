package com.androidxx.yangjw.day36_depend_inject_demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjw on 2016/10/31.
 */
@Target(ElementType.FIELD) //此注解作用于属性上
@Retention(RetentionPolicy.RUNTIME) //表示在vm运行时生效
public @interface BindView {
    int value();
}

package com.androidxx.yangjw.day37_dagger_demo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangjw on 2016/11/1.
 * 使用Module进行注解的类，好比是药水（也就是对象源，提供对象）
 */
@Module
public class AppModule {

    //Provides注解表示由此方法提供对象
    @Provides
    public User provideUser() {
        return new ZhangsanUser();
    }
}

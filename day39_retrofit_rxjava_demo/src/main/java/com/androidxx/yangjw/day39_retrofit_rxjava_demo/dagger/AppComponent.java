package com.androidxx.yangjw.day39_retrofit_rxjava_demo.dagger;

import com.androidxx.yangjw.day39_retrofit_rxjava_demo.MainActivity;

import dagger.Component;

/**
 * Created by yangjw on 2016/11/3.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
}

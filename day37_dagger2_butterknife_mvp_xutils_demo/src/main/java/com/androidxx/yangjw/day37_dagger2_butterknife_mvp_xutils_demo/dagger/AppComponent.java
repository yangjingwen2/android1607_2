package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.dagger;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.MainActivity;

import dagger.Component;

/**
 * Created by yangjw on 2016/11/1.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
}

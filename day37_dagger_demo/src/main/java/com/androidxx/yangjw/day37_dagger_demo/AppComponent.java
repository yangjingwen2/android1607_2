package com.androidxx.yangjw.day37_dagger_demo;

import dagger.Component;

/**
 * Created by yangjw on 2016/11/1.
 * Component:定义一个注射器
 * modules = {AppModule.class}：给注射器灌入药水
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    //将药水注入到MainActivity中的某一个属性（有@Inject的属性）
    void inject(MainActivity activity);
}

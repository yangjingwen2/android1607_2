package com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.dagger;

import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.IGuideModel;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.module.impl.GuideModel;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.IGuidePresenter;
import com.androidxx.yangjw.day37_dagger2_butterknife_mvp_xutils_demo.presenter.impl.GuidePresenter;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangjw on 2016/11/1.
 */
@Module
public class AppModule {

    @Provides
    public Gson provideGson() {
        return new Gson();
    }
    @Provides
    public IGuideModel provideGuideModule(Gson gson) {
        return new GuideModel(gson);
    }
    @Provides
    public IGuidePresenter provideGuidePresenter(IGuideModel guideModel) {
        return new GuidePresenter(guideModel);
    }
}

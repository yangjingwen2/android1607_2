package com.androidxx.yangjw.day36_mvp_demo;

import com.androidxx.yangjw.day36_mvp_demo.model.MainModel;
import com.androidxx.yangjw.day36_mvp_demo.model.bean.GiftBean;
import com.androidxx.yangjw.day36_mvp_demo.presenter.callback.Callback;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        MainModel mainModel = new MainModel();
        String name = mainModel.getName();
        assertEquals("lisi",name);

    }
}
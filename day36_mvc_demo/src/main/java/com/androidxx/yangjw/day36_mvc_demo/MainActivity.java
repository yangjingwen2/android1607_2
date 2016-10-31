package com.androidxx.yangjw.day36_mvc_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidxx.yangjw.day36_mvc_demo.model.DataModel;
import com.androidxx.yangjw.day36_mvc_demo.model.bean.MainBean;
import com.androidxx.yangjw.day36_mvc_demo.ui.DetailsActivity;
import com.androidxx.yangjw.day36_mvc_demo.ui.key.KeyUtils;
import com.androidxx.yangjw.day36_mvc_demo.view.IMainView;

/**
 * Controller:控制器，作用：将Model层数据传递给View层显示，控制数据的显示
 */
public class MainActivity extends AppCompatActivity implements IMainView,View.OnClickListener{

    private DataModel model;
    private TextView mShowTxt;
    private MainBean bean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        model = new DataModel();
        model.queryDatas(this);

    }

    private void initView() {
        mShowTxt = (TextView) findViewById(R.id.main_show_txt);
        mShowTxt.setOnClickListener(this);
    }

    /**
     * 接收到Model返回的数据
     * @param mainBean
     */
    @Override
    public void refreshDatasResult(MainBean mainBean) {
        this.bean = mainBean;
        mShowTxt.setText(mainBean.getAd().get(0).getTitle());
    }

    @Override
    public void onClick(View v) {
        if (this.bean != null) {
            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra(KeyUtils.MainActivity.INTENT_KEY,this.bean.getList().get(0).getId());
            startActivity(intent);
        }

    }
}

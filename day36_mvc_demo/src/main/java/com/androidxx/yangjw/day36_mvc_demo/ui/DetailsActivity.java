package com.androidxx.yangjw.day36_mvc_demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidxx.yangjw.day36_mvc_demo.R;
import com.androidxx.yangjw.day36_mvc_demo.model.DataModel;
import com.androidxx.yangjw.day36_mvc_demo.ui.key.KeyUtils;
import com.androidxx.yangjw.day36_mvc_demo.view.IDetailsView;

public class DetailsActivity extends AppCompatActivity implements IDetailsView{

    private String id;
    private DataModel dataModel;
    private TextView mDetailsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        dataModel = new DataModel();
        id = getIntent().getStringExtra(KeyUtils.MainActivity.INTENT_KEY);
        dataModel.queryDetails(id,this);
    }

    private void initView() {
        mDetailsTxt = (TextView) findViewById(R.id.details_txt);
    }

    @Override
    public void success(String result) {
        mDetailsTxt.setText(result);
    }
}

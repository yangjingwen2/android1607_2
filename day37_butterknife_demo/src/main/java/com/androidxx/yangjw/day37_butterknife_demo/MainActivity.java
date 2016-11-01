package com.androidxx.yangjw.day37_butterknife_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidxx.yangjw.day37_butterknife_demo.adapter.HomeListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_list_lv)
    ListView mListView;
    private HomeListAdapter homeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeListAdapter = new HomeListAdapter(this);
        //加载头布局
        View headerView = LayoutInflater.from(this).inflate(R.layout.home_header_view, null);
        ViewHolder viewHolder = new ViewHolder(headerView);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        mListView.addHeaderView(headerView);
        mListView.setAdapter(homeListAdapter);
    }

    class ViewHolder {
        @BindView(R.id.home_header_iv)
        ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    @OnItemClick(R.id.home_list_lv)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}

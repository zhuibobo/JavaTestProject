package com.example.myapplication18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

public class SwipeRefreshLayoutTest001Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout_test001);
        SwipeRefreshLayout refreshLayout=findViewById(R.id.swipeRefreshLayout1);
        refreshLayout.setOnRefreshListener(()->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //inData(); //初始化数据
                            //listAdapter.notifyDataSetChanged();//更新adapter
                            refreshLayout.setRefreshing(false);//刷新结束，隐藏刷新进度条

                        }
                    });
                }
            }).start();
        });
    }
}

package com.example.zhangxiaoyu01.mydemo.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhangxiaoyu01.mydemo.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SwipeRefreshLayoutActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.mylist)
    ListView mylist;

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            data.add("SwipeRefreshLayout下拉刷新" + i);
        }
        swipeLayout.setColorSchemeResources(R.color.swipe_color1, R.color.swipe_color2, R.color.swipe_color3, R.color.swipe_color4);
        //swipeLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeLayout.setOnRefreshListener(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mylist.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                data.clear();
                for (int i = 0; i < 20; i++) {
                    data.add("SwipeRefreshLayout下拉刷新" + i);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    swipeLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
}

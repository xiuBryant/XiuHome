package com.example.zhangxiaoyu01.mydemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangxiaoyu01.mydemo.R;

import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://beta.kkcredit.cn/ccl/data/ws/rest/app/").build();
    }
}

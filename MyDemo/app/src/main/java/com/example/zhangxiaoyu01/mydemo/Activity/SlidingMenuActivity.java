package com.example.zhangxiaoyu01.mydemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.zhangxiaoyu01.mydemo.R;
import com.example.zhangxiaoyu01.mydemo.View.SlidingMenu;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SlidingMenuActivity extends Activity {

    @Bind(R.id.sliding_menu)
    SlidingMenu sliding_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);
        ButterKnife.bind(this);
    }

    public void onToggle(View view){
        sliding_menu.toggle();
    }
}

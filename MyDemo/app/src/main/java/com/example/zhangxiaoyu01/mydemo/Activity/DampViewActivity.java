package com.example.zhangxiaoyu01.mydemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.zhangxiaoyu01.mydemo.R;
import com.example.zhangxiaoyu01.mydemo.View.DampView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DampViewActivity extends Activity {

    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.dampview)
    DampView dampView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damp_view);
        ButterKnife.bind(this);
        dampView.setImageView(img);
    }

}

package com.example.zhangxiaoyu01.mydemo.Activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.zhangxiaoyu01.mydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoopAnimationActivity extends Activity {

//    @Bind(R.id.moon)
//    ImageView moon;
//    @Bind(R.id.sun)
//    ImageView sun;
//    @Bind(R.id.sky)
//    RelativeLayout sky;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_animation);
        ButterKnife.bind(this);
//        startAnimation();
    }

//    private void startAnimation() {
//
//        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.r1);
//        LinearInterpolator lin = new LinearInterpolator();
//        operatingAnim.setInterpolator(lin);
//        sky.startAnimation(operatingAnim);
//
//        Animation operatingAnim1 = AnimationUtils.loadAnimation(this, R.anim.r1p);
//        operatingAnim1.setInterpolator(lin);
//        sun.startAnimation(operatingAnim1);
//        moon.startAnimation(operatingAnim1);
//
//    }
}

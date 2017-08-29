package com.example.zhangxiaoyu01.mydemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.zhangxiaoyu01.mydemo.R;
import com.example.zhangxiaoyu01.mydemo.View.HorizontalProgressbarWithProgress;
import com.example.zhangxiaoyu01.mydemo.View.RoundProgressbarWithProgress;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProgressActivity extends Activity {

    private static final int MSG_UPDATE = 0;

    @Bind(R.id.progress)
    HorizontalProgressbarWithProgress mProgress;

    @Bind(R.id.roundProgress)
    RoundProgressbarWithProgress mRoundProgress;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = mProgress.getProgress();
            int roundProgress = mRoundProgress.getProgress();
            mProgress.setProgress(++progress);
            mRoundProgress.setProgress(++roundProgress);
            if(progress >= 100){
                mHandler.removeMessages(MSG_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE,100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);
        mHandler.sendEmptyMessage(MSG_UPDATE);
    }
}

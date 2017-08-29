package com.example.zhangxiaoyu01.mydemo.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhangxiaoyu01.mydemo.R;
import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YunActivity extends AppCompatActivity {

    @Bind(R.id.btn_login)
    Button btnLogin;
    private Captcha mCaptcha;

    private UserLoginTask mLoginTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yun);
        ButterKnife.bind(this);
        if(mCaptcha == null){
            mCaptcha = new Captcha(this);
        }

        mCaptcha.setCaptchaId("a05f036b70ab447b87cc788af9a60974");
        mCaptcha.setCaListener(new CaptchaListener() {
            @Override
            public void onReady(boolean b) {

            }

            @Override
            public void closeWindow() {

            }

            @Override
            public void onError(String s) {

            }

            @Override
            public void onValidate(String result, String validate, String s2) {
                if (validate.length() > 0) {
                    toastMsg("验证成功");
                } else {
                    toastMsg("验证失败");
                }
            }

            @Override
            public void onCancel() {
                toastMsg("取消线程");
                if (mLoginTask != null) {
                    if (mLoginTask.getStatus() == AsyncTask.Status.RUNNING) {
                        mLoginTask.cancel(true);
                    }
                }
            }
        });

        mCaptcha.setDebug(false);
        mCaptcha.setTimeout(10000);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginTask = new UserLoginTask();
                mLoginTask.execute();
                mCaptcha.start();
            }
        });
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        UserLoginTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //可选：简单验证DeviceId、CaptchaId、Listener值
            return mCaptcha.checkParams();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                //必填：开始验证
                mCaptcha.Validate();
            } else {
                toastMsg("验证码SDK参数设置错误,请检查配置");
            }
        }

        @Override
        protected void onCancelled() {
            mLoginTask = null;
        }
    }

    private void toastMsg(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

package com.example.zhangxiaoyu01.mydemo.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhangxiaoyu01.mydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.btn_SwipeRefreshLayout)
    Button btn_SwipeRefreshLayout;

    @Bind(R.id.btn_Indicator)
    Button btn_Indicator;

    @Bind(R.id.btn_showDialog)
    Button btn_showDialog;

    @Bind(R.id.btn_slidingMenu)
    Button btn_slidingMenu;

    @Bind(R.id.btn_progressbar)
    Button btn_progressbar;

    @Bind(R.id.btn_wuziqi)
    Button btn_wuziqi;

    @Bind(R.id.btn_animation)
    Button btn_animation;

    @Bind(R.id.btn_dampView)
    Button btn_dampView;

    @Bind(R.id.btn_circleView)
    Button btn_circleView;

    @Bind(R.id.btn_toolBar)
    Button btn_toolBar;

    @Bind(R.id.btn_coordinatorLayout)
    Button btn_coordinatorLayout;

    @Bind(R.id.btn_calander)
    Button btn_calander;

    @Bind(R.id.btn_toggle)
    Button btn_toggle;

    @Bind(R.id.btn_mvp_dagger2)
    Button btn_mvp_dagger2;

    @Bind(R.id.btn_retrofit)
    Button btn_retrofit;

    @Bind(R.id.btn_choose_period)
    Button btn_choose_period;

    @Bind(R.id.btn_yun)
    Button btnYun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        btn_SwipeRefreshLayout.setOnClickListener(this);
        btn_Indicator.setOnClickListener(this);
        btn_showDialog.setOnClickListener(this);
        btn_slidingMenu.setOnClickListener(this);
        btn_progressbar.setOnClickListener(this);
        btn_wuziqi.setOnClickListener(this);
        btn_animation.setOnClickListener(this);
        btn_dampView.setOnClickListener(this);
        btn_circleView.setOnClickListener(this);
        btn_toolBar.setOnClickListener(this);
        btn_coordinatorLayout.setOnClickListener(this);
        btn_calander.setOnClickListener(this);
        btn_toggle.setOnClickListener(this);
        btn_mvp_dagger2.setOnClickListener(this);
        btn_retrofit.setOnClickListener(this);
        btn_choose_period.setOnClickListener(this);
        btnYun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_SwipeRefreshLayout:
                startActivity(new Intent(this, SwipeRefreshLayoutActivity.class));
                break;
            case R.id.btn_Indicator:
                startActivity(new Intent(this, IndicatorActivity.class));
                break;
            case R.id.btn_showDialog:
                showDialog();
                break;
            case R.id.btn_slidingMenu:
                startActivity(new Intent(this, SlidingMenuActivity.class));
                break;
            case R.id.btn_progressbar:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case R.id.btn_wuziqi:
                startActivity(new Intent(this, WuziqiActivity.class));
                break;
            case R.id.btn_animation:
                startActivity(new Intent(this, LoopAnimationActivity.class));
                break;
            case R.id.btn_dampView:
                startActivity(new Intent(this, DampViewActivity.class));
                break;
            case R.id.btn_circleView:
                startActivity(new Intent(this, CircleViewActivity.class));
                break;
            case R.id.btn_toolBar:
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case R.id.btn_coordinatorLayout:
                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
            case R.id.btn_calander:
                startActivity(new Intent(this, CalanderActivity.class));
                break;
            case R.id.btn_toggle:
                startActivity(new Intent(this, ToggleActivity.class));
                break;
            case R.id.btn_mvp_dagger2:
                startActivity(new Intent(this, MvpDaggerActivity.class));
                break;
            case R.id.btn_retrofit:
                break;
            case R.id.btn_choose_period:
                break;
            case R.id.btn_yun:
                startActivity(new Intent(this,YunActivity.class));
                break;
        }
    }

    private void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.submit_userinfo_dialog, null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvIdCard = (TextView) view.findViewById(R.id.tv_idCard);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        Button btnSure = (Button) view.findViewById(R.id.btn_sure);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        tvName.setText("一休");
        tvIdCard.setText("123456789");
        tvTitle.setText("特别提示");
        tvContent.setText("身份有误将影响授信结果，一经确认无法更改，请确认");

        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.dialog).create();
        dialog.show();
        dialog.setContentView(view);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = Dp2Px(this, 297);
        dialog.getWindow().setAttributes(params);
    }

    public int Dp2Px(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}

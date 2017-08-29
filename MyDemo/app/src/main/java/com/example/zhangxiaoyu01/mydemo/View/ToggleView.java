package com.example.zhangxiaoyu01.mydemo.View;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ZXY on 2017/1/16.
 */

public class ToggleView extends View {

    private float slidingX;//做动画时滑动的距离

    private Paint mBgPaint;
    private Paint mBtnPaint;
    private Paint mOffPaint;
    private Paint mOnPaint;

    private RectF bgRect = new RectF();

    private RectF btnRect = new RectF();

    private boolean isOpen;

    //记录文本中心点 cx1:绘制文本1的x坐标  cx2:绘制文本2的x坐标
    //cy记录绘制文本的高度
    float cx1, cy, cx2;

    public ToggleView(Context context) {
        this(context, null);
    }

    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(Color.parseColor("#53c6ff"));
        mBgPaint.setStyle(Paint.Style.STROKE);

        mBtnPaint = new Paint();
        mBtnPaint.setAntiAlias(true);
        mBtnPaint.setColor(Color.parseColor("#53c6ff"));
        mBtnPaint.setStyle(Paint.Style.FILL);

        mOffPaint = new Paint();
        mOffPaint.setAntiAlias(true);
        mOffPaint.setColor(Color.WHITE);
        mOffPaint.setTextSize(sp2px(12));

        mOnPaint = new Paint();
        mOnPaint.setAntiAlias(true);
        mOnPaint.setColor(Color.parseColor("#666666"));
        mOnPaint.setTextSize(sp2px(12));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        bgRect.left = 0;
        bgRect.top = 0;
        bgRect.right = getWidth();
        bgRect.bottom = getHeight();

        if (isOpen) {
            btnRect.left = getWidth() * 0.4f - slidingX;
            btnRect.top = 0;
            btnRect.right = getWidth() - slidingX;
            btnRect.bottom = getHeight();
            mOffPaint.setColor(Color.parseColor("#666666"));
            mOnPaint.setColor(Color.WHITE);
            if (slidingX == 0.4 * getWidth()) {
                isOpen = false;
                mOffPaint.setColor(Color.WHITE);
                mOnPaint.setColor(Color.parseColor("#666666"));
            }
        } else {
            btnRect.left = slidingX;
            btnRect.top = 0;
            btnRect.right = getWidth() * 0.6f + slidingX;
            btnRect.bottom = getHeight();
            mOffPaint.setColor(Color.WHITE);
            mOnPaint.setColor(Color.parseColor("#666666"));
            if (slidingX == 0.4 * getWidth()) {
                isOpen = true;
                mOffPaint.setColor(Color.parseColor("#666666"));
                mOnPaint.setColor(Color.WHITE);
            }
        }

        cy = getHeight() / 2 + (-(mOffPaint.descent() + mOffPaint.ascent()) / 2);
        cx1 = getWidth() * 0.6f / 2 - mOffPaint.measureText("关") / 2;
        cx2 = getWidth() * 0.6f + getWidth() * 0.4f / 2 - mOffPaint.measureText("开") / 2;
        canvas.drawRoundRect(bgRect, dp2px(15), dp2px(15), mBgPaint);
        canvas.drawRoundRect(btnRect, dp2px(15), dp2px(15), mBtnPaint);
        canvas.drawText("关", cx1, cy, mOffPaint);
        canvas.drawText("开", cx2, cy, mOnPaint);

        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                slidingX = 0;
                ValueAnimator animator = ObjectAnimator.ofFloat(0.0f, 0.4f);
                animator.setDuration(200);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        slidingX = ((float) animation.getAnimatedValue()) * getWidth();
                        invalidate();
                    }
                });
                animator.start();
                break;
        }
        return true;
    }

    public int getToggleState() {
        return isOpen ? 1 : 0;
    }


    protected int dp2px(int dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,getResources().getDisplayMetrics());
    }
}

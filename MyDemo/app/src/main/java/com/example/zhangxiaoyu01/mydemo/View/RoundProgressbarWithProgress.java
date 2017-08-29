package com.example.zhangxiaoyu01.mydemo.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.zhangxiaoyu01.mydemo.R;

/**
 * Created by zhangxiaoyu01 on 2016/10/10.
 */
public class RoundProgressbarWithProgress extends HorizontalProgressbarWithProgress {

    private int mRadius = dp2px(30);
    private int mMaxPaintWidth;

    public RoundProgressbarWithProgress(Context context) {
        this(context, null);
    }

    public RoundProgressbarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressbarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        mReachHeight = (int) (mUnReachHeight * 2.5f);
        mReachHeight = mUnReachHeight;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressbarWithProgress);
        mRadius = (int) ta.getDimension(R.styleable.RoundProgressbarWithProgress_radius,mRadius);
        ta.recycle();

        //设置画笔属性
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动
        mPaint.setStrokeCap(Paint.Cap.ROUND);//笔触
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMaxPaintWidth = Math.max(mReachHeight,mUnReachHeight);
        //默认四个padding一致
        int expect = mRadius * 2 + mMaxPaintWidth + getPaddingLeft() + getPaddingRight();
        int width = resolveSize(expect,widthMeasureSpec);
        int height = resolveSize(expect,heightMeasureSpec);

        int realWidth = Math.min(width,height);
        mRadius = (realWidth - getPaddingLeft() - getPaddingRight() - mMaxPaintWidth) / 2;

        setMeasuredDimension(realWidth,realWidth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress() + "%";
        float textWidth = mPaint.measureText(text);
        float textHeight = mPaint.descent() + mPaint.ascent();
        canvas.save();
        canvas.translate(getPaddingLeft() + mMaxPaintWidth / 2, getPaddingTop() + mMaxPaintWidth / 2);
        mPaint.setStyle(Paint.Style.STROKE);
        //draw unreach bar
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        //draw reach bar
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(new RectF(0,0,mRadius * 2,mRadius * 2),0,sweepAngle,false,mPaint);
        //draw text
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,mRadius - textWidth / 2,mRadius - textHeight / 2,mPaint);

        canvas.restore();
    }
}

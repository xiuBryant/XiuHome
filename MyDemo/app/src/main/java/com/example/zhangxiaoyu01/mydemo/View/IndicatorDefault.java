package com.example.zhangxiaoyu01.mydemo.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangxiaoyu01.mydemo.R;

import java.util.List;

/**
 * Created by zhangxiaoyu01 on 2016/8/23.
 */
public class IndicatorDefault extends LinearLayout implements ViewPager.OnPageChangeListener {

    private Paint mPaint;

    private Path mPath;

    private int mTriangleWidth;//三角形的宽度

    private int mTriangleHeight;//三角形的高度

    private int mInitTranslate;//三角形的初始化偏移量

    private int mTranslate;//滑动过程中的偏移量

    private static final float RADIO_WIDTH = 1 / 6f;//三角形宽度占tab宽度的比例

    private int mVisibleTabCount;//自定义的tab可见数量

    private static final int DEFAULT_TAB_VISIBLE_TAB = 3;

    private List<String> mTitles;

    private OnPageChangeListener mListener;

    private ViewPager mViewPager;


    public IndicatorDefault(Context context, AttributeSet attrs) {
        super(context, attrs);


        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorDefault);
        mVisibleTabCount = typedArray.getInt(R.styleable.IndicatorDefault_mVisibleTabCount, DEFAULT_TAB_VISIBLE_TAB);
        if (mVisibleTabCount <= 0) {
            mVisibleTabCount = DEFAULT_TAB_VISIBLE_TAB;
        }
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setPathEffect(new CornerPathEffect(3));//让尖角平滑
        mPaint.setStyle(Paint.Style.FILL);
    }

    public IndicatorDefault(Context context) {
        this(context, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        //重新设置子view的宽度
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LinearLayout.LayoutParams params = (LayoutParams) child.getLayoutParams();
            params.weight = 0;
            params.width = (int) (getScreenWidth() / mVisibleTabCount);
            child.setLayoutParams(params);
        }
        setTvClickListener();
    }

    //获取屏幕的宽
    public float getScreenWidth() {
        WindowManager manager = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / mVisibleTabCount * RADIO_WIDTH);
        mTriangleHeight = mTriangleWidth / 2;
        mInitTranslate = w / mVisibleTabCount / 2 - mTriangleWidth / 2;
        initTriangle();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mInitTranslate + mTranslate, getHeight());
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    private void initTriangle() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    public void bindViewPager(ViewPager viewPager, int position) {
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int tabWidth = getWidth() / mVisibleTabCount;
        //三角形移动
        mTranslate = (int) (position * tabWidth + positionOffset * tabWidth);
        //indicator联动
        if (position >= (mVisibleTabCount - 2) && position < getChildCount() - 2 && getChildCount() > mVisibleTabCount) {
            if (mVisibleTabCount != 1) {
                this.scrollTo((int) ((position - (mVisibleTabCount - 2)) * tabWidth + positionOffset * tabWidth), 0);
            } else {
                this.scrollTo((int) (position * tabWidth + positionOffset * tabWidth), 0);
            }
        }

        if (mListener != null) {
            mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        if (mListener != null) {
            mListener.onPageSelected(position);
        }
        highLightTextView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(state);
        }
    }


    public void setTabTitles(List<String> titles) {
        if (titles != null && titles.size() != 0) {
            mTitles = titles;
            this.removeAllViews();
            for (String title : titles) {
                TextView tv = new TextView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.width = (int) (getScreenWidth() / mVisibleTabCount);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER);
                tv.setText(title);
                tv.setClickable(true);
                this.addView(tv);
            }
            setTvClickListener();
        }
    }

    public interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

    public void resetTextView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(0xff000000);
            }
        }
    }

    public void highLightTextView(int position) {
        resetTextView();
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(0x77ffffff);
        }
    }

    public void setTvClickListener() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }
}

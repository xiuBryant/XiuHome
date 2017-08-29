package com.example.zhangxiaoyu01.mydemo.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.zhangxiaoyu01.mydemo.R;
import com.nineoldandroids.view.ViewHelper;


/**
 * Created by zhangxiaoyu01 on 2016/9/23.
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;

    private float mMenuWidth;
    private float mContentWidth;

    private float mScreenWidth;
    private float mMenuRightPadding;

    private boolean once = false;

    private boolean isOpen;

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
    }

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            mWapper = (LinearLayout) getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mContentWidth = mScreenWidth;
            mContent.getLayoutParams().width = (int) mContentWidth;
            mMenu.getLayoutParams().width = (int) mMenuWidth;
            once = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo((int) mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                float scrollX = getScrollX();
                if (scrollX > mMenuWidth / 2) {
                    this.smoothScrollTo((int) mMenuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth;//1-0

        float rightScale = 0.7f + scale * 0.3f;
        float leftScale = 1.0f - scale * 0.3f;
        float leftAlpha = 0.3f + 0.4f * (1 - scale);

        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, leftAlpha);

        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);
    }

    public void open() {
        if (isOpen) return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    public void close() {
        if (!isOpen) return;
        this.smoothScrollTo((int) mMenuWidth, 0);
        isOpen = false;
    }

    public void toggle() {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }
}

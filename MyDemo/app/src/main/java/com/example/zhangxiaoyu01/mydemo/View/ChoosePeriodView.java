package com.example.zhangxiaoyu01.mydemo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by ZXY on 2017/7/28.
 */

public class ChoosePeriodView extends LinearLayout {

    public ChoosePeriodView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChoosePeriodView(Context context) {
        super(context);
    }

    public interface OnButtonChangeListener{
        void onChange(int choose);
    }


}

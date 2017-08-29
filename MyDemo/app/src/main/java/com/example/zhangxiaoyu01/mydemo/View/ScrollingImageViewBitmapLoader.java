package com.example.zhangxiaoyu01.mydemo.View;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by thijs on 22-03-16.
 */
public interface ScrollingImageViewBitmapLoader {
    Bitmap loadBitmap(Context context, int resourceId);
}

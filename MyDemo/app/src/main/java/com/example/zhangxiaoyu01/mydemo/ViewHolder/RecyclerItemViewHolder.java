package com.example.zhangxiaoyu01.mydemo.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhangxiaoyu01.mydemo.R;

/**
 * Created by zhangxiaoyu01 on 2016/12/9.
 */

public class RecyclerItemViewHolder extends RecyclerView.ViewHolder{

    private final TextView mItemTextView;

    public RecyclerItemViewHolder(View itemView) {
        super(itemView);
        mItemTextView = (TextView) itemView.findViewById(R.id.item_text);
    }

    public static RecyclerItemViewHolder newInstance(View itemView){
            return new RecyclerItemViewHolder(itemView);
    }

    public void setItemText(CharSequence text){
        mItemTextView.setText(text);
    }
}

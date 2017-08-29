package com.example.zhangxiaoyu01.mydemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangxiaoyu01.mydemo.R;
import com.example.zhangxiaoyu01.mydemo.ViewHolder.RecyclerHeaderViewHolder;
import com.example.zhangxiaoyu01.mydemo.ViewHolder.RecyclerItemViewHolder;

import java.util.List;

/**
 * Created by zhangxiaoyu01 on 2016/12/8.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<String> mItemList;
    private Context mContext;

    public RecyclerViewAdapter(List<String> itemList, Context context){
        mItemList = itemList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            return RecyclerItemViewHolder.newInstance(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.header, parent, false);
            return new RecyclerHeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isPositionHeader(position)) {
            RecyclerItemViewHolder viewHolder = (RecyclerItemViewHolder) holder;
            String itemText = mItemList.get(position - 1);
            viewHolder.setItemText(itemText);
        }
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

}

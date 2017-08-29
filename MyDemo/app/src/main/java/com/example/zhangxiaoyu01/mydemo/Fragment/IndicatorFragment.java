package com.example.zhangxiaoyu01.mydemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhangxiaoyu01 on 2016/8/23.
 */
public class IndicatorFragment extends Fragment {


    private static final String BUNDLE_TITLE = "title";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = bundle.getString(BUNDLE_TITLE);
        TextView tv = new TextView(getActivity());
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public static Fragment newInstance(String title){
        IndicatorFragment fragment = new IndicatorFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }
}

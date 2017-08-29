package com.example.zhangxiaoyu01.mydemo.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;

import com.example.zhangxiaoyu01.mydemo.Fragment.IndicatorFragment;
import com.example.zhangxiaoyu01.mydemo.R;
import com.example.zhangxiaoyu01.mydemo.View.IndicatorDefault;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IndicatorActivity extends FragmentActivity {

    @Bind(R.id.mViewPager)
    ViewPager mViewPager;

    @Bind(R.id.mIncaditor)
    IndicatorDefault mIncaditor;

    private List<String> mTitles = Arrays.asList("山西","湖北","河南","江西","新疆","辽宁","吉林","广东","福建");

    private List<Fragment> mContent = new ArrayList<>();

    private FragmentPagerAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_indicator);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initData() {
        for (String title : mTitles){
            Fragment fragment = IndicatorFragment.newInstance(title);
            mContent.add(fragment);
        }
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager(),mContent);
    }

    private void initEvent() {
        mViewPager.setAdapter(mAdapter);
        mIncaditor.bindViewPager(mViewPager,5);
        mIncaditor.setTabTitles(mTitles);
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter{

        private List<Fragment> mData;

        public MyFragmentAdapter(FragmentManager fm,List<Fragment> mList) {
            super(fm);
            mData = mList;
        }

        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }
    }

}

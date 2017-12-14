package com.chen.reader.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager通用适配器
 * Created by Administrator on 2017/12/13.
 */

public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private String[] title;
    private List<Fragment> mFragment = new ArrayList<>();

    public CommonViewPagerAdapter(FragmentManager fm,String[] titles) {
        super(fm);
        title = titles;
    }

    public void addFragment(Fragment fragment){
        mFragment.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    public CharSequence getpageTitle(int position){
        return  title[position];
    }
}

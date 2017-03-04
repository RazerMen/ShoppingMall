package com.atguigu.shoppingmall.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.shoppingmall.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 一名程序员 on 2017/3/4.
 * <p>
 * 作用：viewpager的适配器
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;

    public CommunityViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
package com.shengyuan.beadhouse.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Fragment Adapter
 * Created by dell on 2017/11/5.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    private List<String> tabTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragmentList = fragments;
        this.tabTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 此方法是给tablayout中的tab赋值的，就是显示名称
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}

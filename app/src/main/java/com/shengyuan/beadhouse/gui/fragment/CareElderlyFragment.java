package com.shengyuan.beadhouse.gui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 关爱老人Fragment
 * Created by dell on 2017/11/5.
 */

public class CareElderlyFragment extends BaseFragment {
    private TabLayout tabLayout;

    private List<Fragment> fragmentList;

    private FragmentAdapter fragmentAdapter;

    private List<String> tabTitleList;

    private ViewPager viewPager;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_elderly;
    }

    @Override
    protected void initView(View rootView) {
//        showCenterView();

        init();
        tabLayout =  rootView.findViewById(R.id.care_elderly_tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager =  rootView.findViewById(R.id.care_elderly_tab_viewpager);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        showCenterView();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        tabTitleList = new ArrayList<>();

        fragmentList.add(new CareServiceViewFragment());
        fragmentList.add(new CareListFragment());

        tabTitleList.add(getResources().getString(R.string.care_all_view));
        tabTitleList.add(getResources().getString(R.string.care_elderly_list));

        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentList, tabTitleList);
    }
}

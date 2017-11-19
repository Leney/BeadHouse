package com.shengyuan.beadhouse.gui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.FragmentAdapter;
import com.shengyuan.beadhouse.gui.activity.OldManDetailActivity;
import com.shengyuan.beadhouse.model.CareListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 关爱老人Fragment
 * Created by dell on 2017/11/5.
 */

public class CareElderlyFragment extends BaseFragment implements CareListFragment.OnSelectedItemListener, View.OnClickListener {
    private TabLayout tabLayout;

    private List<Fragment> fragmentList;

    private FragmentAdapter fragmentAdapter;

    private List<String> tabTitleList;

    private ViewPager viewPager;

    private ImageView icon;
    private TextView name;
    /**
     * 当前选中的老人对象
     */
    private CareListBean curSelectedBean;

    /**
     * 服务总览Fragment
     */
    private CareServiceViewFragment careServiceViewFragment;

    /**
     * 关注老人列表Fragment
     */
    private CareListFragment careListFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_elderly;
    }

    @Override
    protected void initView(View rootView) {
//        showCenterView();

        icon = rootView.findViewById(R.id.care_elderly_user_icon);
        name = rootView.findViewById(R.id.care_elderly_name);
        rootView.findViewById(R.id.care_elderly_info_btn).setOnClickListener(this);


        init();
        tabLayout = rootView.findViewById(R.id.care_elderly_tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = rootView.findViewById(R.id.care_elderly_tab_viewpager);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        showCenterView();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        tabTitleList = new ArrayList<>();

        careServiceViewFragment = new CareServiceViewFragment();
        careListFragment = new CareListFragment();
        // 设置老人列表item监听
        careListFragment.setOnSelectedItemListener(this);

        fragmentList.add(careServiceViewFragment);
        fragmentList.add(careListFragment);

        tabTitleList.add(getResources().getString(R.string.care_all_view));
        tabTitleList.add(getResources().getString(R.string.care_elderly_list));

        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentList, tabTitleList);
    }

    @Override
    public void onSelected(CareListBean bean) {
        // 老人列表选择item 发生改变的监听
        if (bean == null) return;
        curSelectedBean = bean;
        GlideLoader.loadNetWorkResource(getActivity(), curSelectedBean.icon, icon, true);
        name.setText(curSelectedBean.name + " " + curSelectedBean.age + "岁");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.care_elderly_info_btn:
                // 老人资料卡
                if (curSelectedBean == null) return;
                OldManDetailActivity.startActivity(getActivity(), curSelectedBean.id);
                break;
        }
    }
}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.FragmentAdapter;
import com.shengyuan.beadhouse.gui.fragment.LoginByCodeFragment;
import com.shengyuan.beadhouse.gui.fragment.LoginByPasswordFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录界面
 * Created by dell on 2017/11/5.
 */

public class LoginActivity extends BaseActivity {
    private TabLayout tabLayout;

    private List<Fragment> fragmentList;

    private FragmentAdapter fragmentAdapter;

    private List<String> tabTitleList;

    private ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.login));
        baseTitle.setRightText(getResources().getString(R.string.register));
        init();
        tabLayout = (TabLayout) findViewById(R.id.login_tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = (ViewPager) findViewById(R.id.login_tab_viewpager);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        baseTitle.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注册点击事件
                RegisterActivity.startActivity(LoginActivity.this);
            }
        });
        showCenterView();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        tabTitleList = new ArrayList<>();

        fragmentList.add(new LoginByPasswordFragment());
        fragmentList.add(new LoginByCodeFragment());

        tabTitleList.add(getResources().getString(R.string.login_by_pwd));
        tabTitleList.add(getResources().getString(R.string.login_by_code));

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, tabTitleList);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

}

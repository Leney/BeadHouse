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

    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        phone = getIntent().getStringExtra("phone");
        baseTitle.setTitleName(getResources().getString(R.string.login));
        baseTitle.setRightText(getResources().getString(R.string.register));
        baseTitle.setRightVisible(true);
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

        fragmentList.add(LoginByPasswordFragment.newInstance(phone));
        fragmentList.add(LoginByCodeFragment.newInstance(phone));

        tabTitleList.add(getResources().getString(R.string.login_by_pwd));
        tabTitleList.add(getResources().getString(R.string.login_by_code));

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, tabTitleList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int length = fragmentList.size();
        for (int i = 0; i < length; i++) {
            if (fragmentList.get(i) == null) continue;
            fragmentList.get(i).onActivityResult(requestCode, resultCode, data);
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    /**
     * 启动登陆界面，传入账号(phone)
     *
     * @param context
     * @param phone
     */
    public static void startActivity(Context context, String phone) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

}

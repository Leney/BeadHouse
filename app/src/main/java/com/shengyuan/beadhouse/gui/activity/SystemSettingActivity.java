package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 系统设置
 * Created by dell on 2017/11/19.
 */

public class SystemSettingActivity extends BaseActivity implements View.OnClickListener {
    private TextView servicePhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_setting;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.system_setting));
        findViewById(R.id.system_setting_about_us_lay).setOnClickListener(this);
        findViewById(R.id.system_setting_feed_back_lay).setOnClickListener(this);
        findViewById(R.id.system_setting_help_center_lay).setOnClickListener(this);
        findViewById(R.id.system_setting_service_phone_lay).setOnClickListener(this);
        findViewById(R.id.system_setting_login_out_btn).setOnClickListener(this);
        servicePhone = (TextView) findViewById(R.id.system_setting_service_phone);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.system_setting_about_us_lay:
                // 关于我们
                break;
            case R.id.system_setting_feed_back_lay:
                // 意见反馈
                break;
            case R.id.system_setting_help_center_lay:
                // 帮助中心
                WebActivity.startActivity(SystemSettingActivity.this, "http://www.baidu.com", getResources().getString(R.string.help_center));
                break;
            case R.id.system_setting_service_phone_lay:
                // 客服热线
                break;
            case R.id.system_setting_login_out_btn:
                // 退出登录
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SystemSettingActivity.class));
    }
}

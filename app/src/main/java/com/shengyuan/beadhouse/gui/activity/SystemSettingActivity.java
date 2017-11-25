package com.shengyuan.beadhouse.gui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
                AboutUsActivity.startActivity(SystemSettingActivity.this);
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
                String phone = servicePhone.getText().toString();
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));//直接拨打电话
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Toast.makeText(SystemSettingActivity.this,"请到设置中打开拨打电话权限!",Toast.LENGTH_SHORT).show();
                    return;
                }
                SystemSettingActivity.this.startActivity(dialIntent);
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

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * Created by dell on 2017/11/25.
 */

public class AboutUsActivity extends BaseActivity {
    private TextView version, info;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.about_us));
        version = (TextView) findViewById(R.id.about_us_version);
        info = (TextView) findViewById(R.id.about_us_info);
        version.setText(getVersion());
        showCenterView();
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "v1.0";
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,AboutUsActivity.class));
    }
}

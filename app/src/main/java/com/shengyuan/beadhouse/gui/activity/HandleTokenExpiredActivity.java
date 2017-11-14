package com.shengyuan.beadhouse.gui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.gui.dialog.HandelTokenExpireDialog;
import com.shengyuan.beadhouse.util.ActivityUtils;


/**
 * Created by ${xingen} on 2017/11/8.
 * <p>
 * 一个全局的Dialog用于，处理token过期
 */

public class HandleTokenExpiredActivity extends Activity {
    private HandelTokenExpireDialog handelTokenExpireDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        showDialog();
        handleTokenData();
    }

    /**
     * 清空token
     */
    private void handleTokenData() {
        BHApplication.getInstance().setToken("");
        // TODO 清空数据库  ObservableUtils类在rxjava的包下面，里面有rxjava操作的参考
//        ObservableUtils.createDeletePersonMSG().compose(SubscribeUtils.createTransformer()).subscribe();
    }

    private void showDialog() {
        this.handelTokenExpireDialog = new HandelTokenExpireDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handelTokenExpireDialog.dismiss();
                ActivityUtils.finishAllActivity();
                LoginActivity.startActivity(HandleTokenExpiredActivity.this);
            }
        });


//        this.handelTokenExpireDialog = new HandelTokenExpireDialog(this, view -> {
//            handelTokenExpireDialog.dismiss();
//            ActivityUtils.finishAllActivity();
//            LoginActivity.startActivity(HandleTokenExpiredActivity.this);
//        });
        if (!this.handelTokenExpireDialog.isShowing()) {
            this.handelTokenExpireDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handelTokenExpireDialog != null && handelTokenExpireDialog.isShowing()) {
            handelTokenExpireDialog.dismiss();
        }
        ActivityUtils.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

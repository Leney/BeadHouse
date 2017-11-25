package com.shengyuan.beadhouse.gui.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;

/**
 * 退出登录dialog
 * Created by dell on 2017/11/25.
 */

public class LoginOutDialog extends BottomDialog{
    private TextView loginOutBtn, cancelBtn;

    public LoginOutDialog(Activity activity) {
        super(activity, R.layout.dialog_login_out);
        initView();
    }

    private void initView() {
        loginOutBtn = findViewById(R.id.dialog_login_out_btn);
        loginOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 退出登录
                dismiss();
            }
        });
        cancelBtn = findViewById(R.id.dialog_login_out_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

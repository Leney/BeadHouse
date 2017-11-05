package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 设置新的密码界面
 * Created by dell on 2017/11/5.
 */

public class SetNewPwdActivity extends BaseActivity implements View.OnClickListener {
    private EditText newPwdInput, newPwdConfirmInput;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_new_pwd;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.set_new_pwd));
        baseTitle.setRightVisible(false);
        newPwdInput = (EditText) findViewById(R.id.set_new_password_input);
        newPwdConfirmInput = (EditText) findViewById(R.id.set_new_password_confirm_input);
        findViewById(R.id.set_new_pwd_commit_btn).setOnClickListener(this);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        String newPwd = newPwdInput.getText().toString();
        if (newPwd.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.input_set_new_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        String confirmPwd = newPwdConfirmInput.getText().toString();
        if (!newPwd.equals(confirmPwd)) {
            Toast.makeText(this, getResources().getString(R.string.different_new_pwd), Toast.LENGTH_SHORT).show();
        }
        // TODO 提交修改新的密码
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SetNewPwdActivity.class));
    }
}

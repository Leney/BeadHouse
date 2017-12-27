package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ActivityUtils;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置新的密码界面
 * Created by dell on 2017/11/5.
 */

public class SetNewPwdActivity extends BaseActivity implements View.OnClickListener {
    private EditText newPwdInput, newPwdConfirmInput;

    /**
     * 需要修改的手机号码
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;

    private WaitingDialog waitingDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_new_pwd;
    }

    @Override
    protected void initView() {

        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        if (phone.isEmpty() || code.isEmpty()) {
            finish();
            return;
        }

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
        // 提交设置新的密码
        setNewPassword(newPwd);
    }

    /**
     * 请求提交设置新的密码
     */
    private void setNewPassword(String newPwd) {
        if (waitingDialog == null) {
            waitingDialog = new WaitingDialog(SetNewPwdActivity.this);
        }
        waitingDialog.show();
        Map<String, Object> params = new HashMap<>();
        params.put("username", phone);
        params.put("code", code);
        params.put("new_password", newPwd);
        retrofitClient.setNewPassword(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                waitingDialog.dismiss();
                ToastUtils.showToast(getResources().getString(R.string.change_pwd_success));
                // 移除所有Activity
                ActivityUtils.finishAllActivity();
                // 启动登陆界面
                LoginActivity.startActivity(SetNewPwdActivity.this, phone);
            }

            @Override
            public void failure(CommonException e) {
                waitingDialog.dismiss();
                ToastUtils.showToast(e.getMessage());
            }
        });
    }

    public static void startActivity(Context context, String phone, String code) {
        Intent intent = new Intent(context, SetNewPwdActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }
}

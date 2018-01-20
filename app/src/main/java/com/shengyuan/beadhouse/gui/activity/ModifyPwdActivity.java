package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ActivityUtils;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 修改密码
 * Created by dell on 2017/11/12.
 */

public class ModifyPwdActivity extends BaseActivity implements View.OnClickListener {
    private EditText oldPwdInput, newPwdInput;
    private TextView confirmBtn;
    private WaitingDialog waitingDialog;

    private String account;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    protected void initView() {
        account = getIntent().getStringExtra("account");
        baseTitle.setTitleName(getResources().getString(R.string.modify_pwd));
        oldPwdInput = (EditText) findViewById(R.id.modify_pwd_old_pwd_input);
        newPwdInput = (EditText) findViewById(R.id.modify_pwd_new_pwd_input);
        confirmBtn = (TextView) findViewById(R.id.modify_pwd_confirm_btn);
        confirmBtn.setOnClickListener(this);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_pwd_confirm_btn:
                // 确认修改
                String oldPwd = oldPwdInput.getText().toString();
                if (oldPwd.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_old_pwd), Toast.LENGTH_SHORT).show();
                    return;
                }
                String newPwd = newPwdInput.getText().toString();
                if (newPwd.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_new_pwd), Toast.LENGTH_SHORT).show();
                    return;
                }
                // 提交修改密码数据
                modifyPassword(oldPwd, newPwd);
                break;
        }
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     */
    private void modifyPassword(String oldPwd, String newPwd) {
        if (waitingDialog == null) {
            waitingDialog = new WaitingDialog(ModifyPwdActivity.this);
        }
        waitingDialog.show();
        Map<String, Object> params = new HashMap<>();
        params.put("old_password", oldPwd);
        params.put("new_password", newPwd);
        Subscription subscription = retrofitClient.modifyPassword(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                waitingDialog.dismiss();
                ToastUtils.showToast(getResources().getString(R.string.modify_pwd_success));
                UserAccountManager.getInstance().clear(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        ActivityUtils.finishAllActivity();
                        LoginActivity.startActivity(ModifyPwdActivity.this, account);
                    }
                });
            }

            @Override
            public void failure(CommonException e) {
                waitingDialog.dismiss();
                ToastUtils.showToast(e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    public static void startActivity(Context context, String account) {
        Intent intent = new Intent(context, ModifyPwdActivity.class);
        intent.putExtra("account", account);
        context.startActivity(intent);
    }
}

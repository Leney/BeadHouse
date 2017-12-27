package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.view.CountDownTextView;

/**
 * 找回密码，获取验证码界面
 * Created by dell on 2017/11/5.
 */

public class FindBackPwdGetCodeActivity extends BaseActivity implements View.OnClickListener, CountDownTextView.OnCountDownDoneListener {

    private EditText accountInput, codeInput;

    private CountDownTextView getCodeBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_pwd_get_code;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.find_back_pwd));
        baseTitle.setRightVisible(false);
        accountInput = (EditText) findViewById(R.id.find_pwd_account_input);
        codeInput = (EditText) findViewById(R.id.find_pwd_code_input);
        getCodeBtn = (CountDownTextView) findViewById(R.id.find_pwd_get_code_btn);
        findViewById(R.id.find_pwd_commit_btn).setOnClickListener(this);
        getCodeBtn.setOnClickListener(this);

        accountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 只有在输入到11位的时候才能获取验证码
                if (s.length() == 11) {
                    getCodeBtn.setTextColor(ContextCompat.getColor(FindBackPwdGetCodeActivity.this, R.color.main_color));
                    getCodeBtn.setSelected(true);
                } else {
                    if (getCodeBtn.isSelected()) {
                        getCodeBtn.setTextColor(ContextCompat.getColor(FindBackPwdGetCodeActivity.this, R.color.text_555555));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_pwd_get_code_btn:
                // 获取验证码按钮
                if (!getCodeBtn.isSelected()) {
                    Toast.makeText(FindBackPwdGetCodeActivity.this, getResources().getString(R.string.input_right_phone_num), Toast.LENGTH_SHORT).show();
                    return;
                }
                getCodeBtn.start(60);
                // 获取验证码
                String getCodePhone = accountInput.getText().toString().trim();
                compositeSubscription.add(retrofitClient.getMessageCode(getCodePhone));
                break;
            case R.id.find_pwd_commit_btn:
                // 提交按钮
                String phone = accountInput.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_num_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() < 11) {
                    Toast.makeText(this, getResources().getString(R.string.input_right_phone), Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeInput.getText().toString().trim();
                if (code.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_msg_code), Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO 是否需要验证验证码是否正确
                // TODO 测试，直接在这里跳转了
                SetNewPwdActivity.startActivity(FindBackPwdGetCodeActivity.this, phone, code);
                break;
        }
    }

    @Override
    public void onCountDownDone() {
        // 倒计时完成
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, FindBackPwdGetCodeActivity.class));
    }

}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.view.CountDownTextView;

/**
 * 注册界面
 * Created by dell on 2017/11/4.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, CountDownTextView.OnCountDownDoneListener {
    private EditText accountInput, codeInput, pwdInput;
    private TextView registerBtn;
    private CountDownTextView getCodeBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.register));
        baseTitle.setRightVisible(false);
        accountInput = (EditText) findViewById(R.id.register_account_input);
        codeInput = (EditText) findViewById(R.id.register_msg_code_input);
        pwdInput = (EditText) findViewById(R.id.register_password_input);
        registerBtn = (TextView) findViewById(R.id.register_register_btn);
        registerBtn.setOnClickListener(this);
        getCodeBtn = (CountDownTextView) findViewById(R.id.register_get_msg_code_btn);
        getCodeBtn.setOnClickListener(this);
        getCodeBtn.setOnDoneListener(this);
        showCenterView();

        accountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 只有在输入到11位的时候才能获取验证码
                if (s.length() == 11) {
                    getCodeBtn.setTextColor(ContextCompat.getColor(RegisterActivity.this,R.color.main_color));
                    getCodeBtn.setSelected(true);
                } else {
                    if (getCodeBtn.isSelected()) {
                        getCodeBtn.setTextColor(ContextCompat.getColor(RegisterActivity.this,R.color.text_555555));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCodeBtn.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_get_msg_code_btn:
                // 获取验证码
                if (!getCodeBtn.isSelected()) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.input_right_phone_num), Toast.LENGTH_SHORT).show();
                    return;
                }
                getCodeBtn.start(60);
                // TODO 获取验证码

                break;
            case R.id.register_register_btn:
                // 注册
                String phone = accountInput.getText().toString();
                if(phone.isEmpty()){
                    Toast.makeText(this, getResources().getString(R.string.input_num_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone.length() < 11){
                    Toast.makeText(this, getResources().getString(R.string.input_right_phone), Toast.LENGTH_SHORT).show();
                   return;
                }
                String code = codeInput.getText().toString();
                if(code.isEmpty()){
                    Toast.makeText(this, getResources().getString(R.string.input_msg_code), Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = pwdInput.getText().toString();
                if(pwd.isEmpty()){
                    Toast.makeText(this, getResources().getString(R.string.input_pwd_hint), Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO 请求网络注册
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    public void onCountDownDone() {
        // 倒计时完成
    }

}

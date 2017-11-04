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

import com.orhanobut.logger.Logger;
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
                Logger.i("s.length-------->>"+s.length());
                if (s.length() == 11) {
                    Logger.i("设置获取短信验证码颜色！！");
                    getCodeBtn.setTextColor(ContextCompat.getColor(RegisterActivity.this,R.color.main_color));
                    getCodeBtn.setSelected(true);
                } else {
                    Logger.i("短信验证码selected------->>>" + getCodeBtn.isSelected());
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
                if (getCodeBtn.isSelected()) {
                    getCodeBtn.start(60);
                } else {
                    Toast.makeText(this, getResources().getString(R.string.input_right_phone_num), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_register_btn:
                // 注册
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    public void onCountDownDone() {
        // 倒计时完成
        Logger.i("倒计时完成！！！");
    }
}

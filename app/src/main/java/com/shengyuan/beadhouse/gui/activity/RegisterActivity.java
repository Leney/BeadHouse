package com.shengyuan.beadhouse.gui.activity;

import android.app.Activity;
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
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.gui.view.CountDownTextView;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 注册界面
 * Created by dell on 2017/11/4.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, CountDownTextView.OnCountDownDoneListener {
    public static final int REQUEST_CODE = 1101;
    private EditText accountInput, codeInput, pwdInput;
    private TextView registerBtn;
    private CountDownTextView getCodeBtn;

    /**
     * 请稍候dialog
     */
    private WaitingDialog waitingDialog;

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
                    getCodeBtn.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.main_color));
                    getCodeBtn.setSelected(true);
                } else {
                    if (getCodeBtn.isSelected()) {
                        getCodeBtn.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.text_555555));
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
                String phoneNum = accountInput.getText().toString();
                // 获取验证码
                Subscription subscription = retrofitClient.getMessageCode(phoneNum);
                compositeSubscription.add(subscription);
                break;
            case R.id.register_register_btn:
                // 注册
                final String phone = accountInput.getText().toString().trim();
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
                String pwd = pwdInput.getText().toString().trim();
                if (pwd.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_pwd_hint), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (waitingDialog == null) {
                    waitingDialog = new WaitingDialog(RegisterActivity.this);
                }
                waitingDialog.show();
                //请求网络注册
                Map<String, Object> params = new HashMap<>();
                params.put("username", phone);
                params.put("password", pwd);
                params.put("code", code);
                Subscription subscription2 = retrofitClient.register(params, new ResponseResultListener() {
                    @Override
                    public void success(Object o) {
                        waitingDialog.dismiss();
                        // 注册成功跳转到登陆界面
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_success), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("phone",phone);
                        setResult(RESULT_OK,intent);
                        finish();
                    }

                    @Override
                    public void failure(CommonException e) {
                        waitingDialog.dismiss();
                    }
                });
                compositeSubscription.add(subscription2);
                break;
        }
    }

    @Override
    public void onCountDownDone() {
        // 倒计时完成
    }

    public static void startActivity(Activity activity) {
        activity.startActivityForResult(new Intent(activity, RegisterActivity.class),REQUEST_CODE);
    }

}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.view.CountDownTextView;

/**
 * 绑定手机号
 * Created by dell on 2017/11/12.
 */

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener, CountDownTextView.OnCountDownDoneListener {
    private EditText phoneInput;
    private EditText codeInput;
    private CountDownTextView countDownTextView;
    private TextView commitBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.binding_phone));
        phoneInput = (EditText) findViewById(R.id.bind_phone_phone_input);
        codeInput = (EditText) findViewById(R.id.bind_phone_msg_code_input);
        countDownTextView = (CountDownTextView) findViewById(R.id.bind_phone_get_msg_code_btn);
        commitBtn = (TextView) findViewById(R.id.bind_phone_commit_btn);
        countDownTextView.setOnClickListener(this);
        countDownTextView.setOnDoneListener(this);
        commitBtn.setOnClickListener(this);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_phone_get_msg_code_btn:
                // 获取验证码
                countDownTextView.start(60);
                break;
            case R.id.bind_phone_commit_btn:
                // 提交
                String phone = phoneInput.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_num_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() < 11) {
                    Toast.makeText(this, getResources().getString(R.string.input_right_phone), Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeInput.getText().toString();
                if (code.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.input_msg_code), Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO 提交
                break;
        }
    }

    @Override
    public void onCountDownDone() {
        // 倒计时完成
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTextView.stop();
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,BindPhoneActivity.class));
    }
}

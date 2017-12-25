package com.shengyuan.beadhouse.gui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.RegisterActivity;
import com.shengyuan.beadhouse.gui.view.CountDownTextView;

/**
 * 免密登录
 * Created by dell on 2017/11/5.
 */

public class LoginByCodeFragment extends BaseFragment implements View.OnClickListener, CountDownTextView.OnCountDownDoneListener {
    private EditText accountInput, codeInput;
    /**
     * 倒计时控件
     */
    private CountDownTextView getCodeBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_by_code;
    }

    @Override
    protected void initView(View rootView) {
        accountInput = rootView.findViewById(R.id.login_by_code_account_input);
        codeInput = rootView.findViewById(R.id.login_by_code_msg_code_input);
        getCodeBtn = rootView.findViewById(R.id.login_by_code_get_code_btn);
        getCodeBtn.setOnClickListener(this);
        rootView.findViewById(R.id.login_by_code_login_btn).setOnClickListener(this);


        accountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 只有在输入到11位的时候才能获取验证码
                if (s.length() == 11) {
                    getCodeBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.main_color));
                    getCodeBtn.setSelected(true);
                } else {
                    if (getCodeBtn.isSelected()) {
                        getCodeBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_555555));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getCodeBtn.setOnDoneListener(this);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_by_code_get_code_btn:
                // 获取验证码
                if (!getCodeBtn.isSelected()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.input_right_phone_num), Toast.LENGTH_SHORT).show();
                    return;
                }
                getCodeBtn.start(60);
                // TODO 获取验证码

                break;
            case R.id.login_by_code_login_btn:
                // 立即登录
                String phone = accountInput.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.input_num_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() < 11) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.input_right_phone), Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeInput.getText().toString();
                if (code.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.input_msg_code), Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO 立即登录
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RegisterActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // 注册成功返回
            String phone = data.getStringExtra("phone");
            accountInput.setText(phone);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getCodeBtn.stop();
    }

    @Override
    public void onCountDownDone() {
        // 倒计时完成
    }
}

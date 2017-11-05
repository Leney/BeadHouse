package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.FindBackPwdGetCodeActivity;

/**
 * 登录，帐号和密码登录
 * Created by dell on 2017/11/5.
 */

public class LoginByPasswordFragment extends BaseFragment implements View.OnClickListener {
    private EditText accountInput, pwdInput;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_by_pwd;
    }

    @Override
    protected void initView(View rootView) {
        accountInput = rootView.findViewById(R.id.login_by_pwd_account_input);
        pwdInput = rootView.findViewById(R.id.login_by_pwd_pwd_input);
        rootView.findViewById(R.id.login_by_pwd_login_btn).setOnClickListener(this);
        rootView.findViewById(R.id.login_by_pwd_find_pwd).setOnClickListener(this);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_by_pwd_login_btn:
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
                String pwd = pwdInput.getText().toString();
                if (pwd.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.please_input_pwd_hint), Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO 请求登录接口
                break;
            case R.id.login_by_pwd_find_pwd:
                // 找回密码
                FindBackPwdGetCodeActivity.startActivity(getActivity());
                break;
        }
    }
}

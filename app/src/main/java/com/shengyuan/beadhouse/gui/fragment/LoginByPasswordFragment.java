package com.shengyuan.beadhouse.gui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.FindBackPwdGetCodeActivity;
import com.shengyuan.beadhouse.gui.activity.RegisterActivity;
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录，帐号和密码登录
 * Created by dell on 2017/11/5.
 */

public class LoginByPasswordFragment extends BaseFragment implements View.OnClickListener {
    private EditText accountInput, pwdInput;

    private WaitingDialog waitingDialog = null;

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
                String phone = accountInput.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.input_num_hint), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() < 11) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.input_right_phone), Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = pwdInput.getText().toString().trim();
                if (pwd.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.please_input_pwd_hint), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (waitingDialog == null) {
                    waitingDialog = new WaitingDialog(getActivity());
                }
                waitingDialog.show();
                // 请求登录接口
                Map<String, Object> params = new HashMap<>();
                params.put("username", phone);
                params.put("password", pwd);
                params.put("logintype", "pwd");
                retrofitClient.login(params, new ResponseResultListener<LoginBean>() {
                    @Override
                    public void success(LoginBean loginBean) {
                        Log.i("llj", "登陆成功--token--->>>" + loginBean.getToken());
                        BHApplication.getInstance().setToken(loginBean.getToken()+":unused");
//                        BHApplication.getInstance().setToken();
                        // 再去获取登陆用户的个人信息
                        getLoginInfo();
                    }

                    @Override
                    public void failure(CommonException e) {
                        Log.e("llj", "登陆失败,e---->>>" + e.getMessage());
                    }
                });
                break;
            case R.id.login_by_pwd_find_pwd:
                // 找回密码
                FindBackPwdGetCodeActivity.startActivity(getActivity());
                break;
        }
    }

    /**
     * 获取当前登陆用户的信息
     */
    private void getLoginInfo() {
        retrofitClient.getLoginInfo(new ResponseResultListener() {
            @Override
            public void success(Object o) {
                Log.i("llj", "获取登陆用户信息成功--->>>" + o.toString());
                waitingDialog.dismiss();
                getActivity().finish();
            }

            @Override
            public void failure(CommonException e) {
                Log.e("llj", "获取登陆用户信息失败,e---->>>" + e.getMessage());
                waitingDialog.dismiss();
            }
        });
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
}

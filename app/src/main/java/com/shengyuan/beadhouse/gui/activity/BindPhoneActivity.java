package com.shengyuan.beadhouse.gui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.Constance;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.gui.view.CountDownTextView;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 绑定手机号
 * Created by dell on 2017/11/12.
 */

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener, CountDownTextView.OnCountDownDoneListener {
    public static final int REQUEST_CODE = 1002;
    private EditText phoneInput;
    private EditText codeInput;
    private CountDownTextView countDownTextView;
    private TextView commitBtn;

    private WaitingDialog waitingDialog = null;

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

        phoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 只有在输入到11位的时候才能获取验证码
                countDownTextView.setSelected(s.length() == 11);
//                if (s.length() == 11) {
////                    countDownTextView.setTextColor(ContextCompat.getColor(BindPhoneActivity.this, R.color.main_color));
//                    countDownTextView.setSelected(true);
//                } else {
////                    if (countDownTextView.isSelected()) {
////                        countDownTextView.setTextColor(ContextCompat.getColor(BindPhoneActivity.this, R.color.text_555555));
////                    }
//                    countDownTextView.setSelected(false);
//                }
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
            case R.id.bind_phone_get_msg_code_btn:
                // 获取验证码
                if (!countDownTextView.isSelected()) {
                    Toast.makeText(BindPhoneActivity.this, getResources().getString(R.string.input_right_phone_num), Toast.LENGTH_SHORT).show();
                    return;
                }
                countDownTextView.start(60);
                String phoneNum = phoneInput.getText().toString();
                // 获取验证码
                Subscription subscription = retrofitClient.getMessageCode(phoneNum, Constance.TYPE_CHANGE_TEL);
                compositeSubscription.add(subscription);
                break;
            case R.id.bind_phone_commit_btn:
                // 提交
                String phone = phoneInput.getText().toString().trim();
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

                // 提交更改
                modifyPhone(phone,code);
                break;
        }
    }

    /**
     * 修改手机号码
     * @param phone
     * @param code
     */
    private void modifyPhone(String phone,String code){
        if(waitingDialog == null){
            waitingDialog = new WaitingDialog(BindPhoneActivity.this);
        }
        waitingDialog.show();
        Map<String,Object> params = new HashMap<>();
        params.put("new_username",phone);
        params.put("code",code);
        Subscription subscription = retrofitClient.modifyPhone(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                waitingDialog.dismiss();
                UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        // 修改本地帐号信息，并保存
                        loginBean.getUser().setUsername(phone);
                        UserAccountManager.getInstance().update(loginBean, new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                ToastUtils.showToast(getResources().getString(R.string.modify_phone_success));
                                // 发送账户信息改变的广播
                                sendBroadcast(new Intent(Constance.ACTION_MODIFY_USER_INFO));
                                setResult(Activity.RESULT_OK);
                                finish();
                            }
                        });
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

    public static void startActivityForResult(Activity activity){
        activity.startActivityForResult(new Intent(activity,BindPhoneActivity.class),REQUEST_CODE);
    }
}

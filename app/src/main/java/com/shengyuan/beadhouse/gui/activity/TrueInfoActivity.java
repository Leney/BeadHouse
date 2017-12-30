package com.shengyuan.beadhouse.gui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.Constance;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 实名信息界面
 * Created by dell on 2017/11/12.
 */

public class TrueInfoActivity extends BaseActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 1001;
    private TextView account;
    private EditText nameInput;
    private TextView man, woman;
    private ImageView manSelect, womanSelect;
    private EditText cardNumInput;
    private TextView commitBtn;

    /**
     * 当前选中的性别  true = 男，false = 女
     */
    private boolean curSelectMan = true;

    private WaitingDialog waitingDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_true_info;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.true_name));
        account = (TextView) findViewById(R.id.true_info_account_text);
        nameInput = (EditText) findViewById(R.id.true_info_name_input);
        man = (TextView) findViewById(R.id.true_info_sex_man);
        man.setOnClickListener(this);
        woman = (TextView) findViewById(R.id.true_info_sex_woman);
        woman.setOnClickListener(this);
        manSelect = (ImageView) findViewById(R.id.true_info_man_select);
        womanSelect = (ImageView) findViewById(R.id.true_info_woman_select);
        cardNumInput = (EditText) findViewById(R.id.true_info_card_num_input);
        commitBtn = (TextView) findViewById(R.id.true_info_commit_btn);
        commitBtn.setOnClickListener(this);

        // 获取个人信息
        getPersonalInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.true_info_commit_btn:
                // 提交按钮
                String name = nameInput.getText().toString().trim();
                if (name.isEmpty()) {
                    ToastUtils.showToast(getResources().getString(R.string.input_true_name));
                    return;
                }

                String cardId = cardNumInput.getText().toString().trim();
                if (cardId.isEmpty() || cardId.length() != 18) {
                    ToastUtils.showToast(getResources().getString(R.string.input_right_card_no));
                    return;
                }

                // 提交完善信息
                commitInfo(name, curSelectMan ? "男" : "女", cardId);
                break;
            case R.id.true_info_sex_man:
                // 男
                setSelectSex(0);
                break;
            case R.id.true_info_sex_woman:
                // 女
                setSelectSex(1);
                break;
        }
    }

    /**
     * 获取当前登陆的个人信息
     */
    private void getPersonalInfo() {
        UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean bean) {
                // 设置个人信息显示
                setInfoView(bean);
                showCenterView();
            }
        });
    }

    /**
     * 完善个人信息提交
     */
    private void commitInfo(final String name, final String sex, final String cardId) {
        if (waitingDialog == null) {
            waitingDialog = new WaitingDialog(TrueInfoActivity.this);
        }
        waitingDialog.show();

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("sex", sex);
        params.put("ID_number", cardId);
        Subscription subscription = retrofitClient.perfectPersonalInfo(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                waitingDialog.dismiss();
                updateAccountInfo(name, sex, cardId);
            }

            @Override
            public void failure(CommonException e) {
                waitingDialog.dismiss();
                ToastUtils.showToast(e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 更新个人数据到本地数据库
     */
    private void updateAccountInfo(String name, String sex, String cardId) {
        Subscription subscription = UserAccountManager.getInstance().perfectUserInfo(name, sex, cardId, new Action1<LoginBean>() {
            @Override
            public void call(LoginBean bean) {
                ToastUtils.showToast(getResources().getString(R.string.perfect_info_success));
                // 发送账户信息改变的广播
                sendBroadcast(new Intent(Constance.ACTION_MODIFY_USER_INFO));
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 设置个人数据信息显示
     *
     * @param bean
     */
    private void setInfoView(LoginBean bean) {
        if (bean == null) return;
        account.setText(bean.getUser().getUsername());
        nameInput.setText(bean.getUser().getName());
        cardNumInput.setText(bean.getUser().getID_number());
        if (bean.getUser().getSex().equals("男")) {
            setSelectSex(0);
        } else {
            setSelectSex(1);
        }
    }

    /**
     * 设置显示性别选择的视图
     *
     * @param sex 0=男，1=女
     */
    private void setSelectSex(int sex) {
        if (sex == 0) {
            curSelectMan = true;
            man.setTextColor(ContextCompat.getColor(TrueInfoActivity.this, R.color.main_color));
            woman.setTextColor(ContextCompat.getColor(TrueInfoActivity.this, R.color.text_666666));
            manSelect.setVisibility(View.VISIBLE);
            womanSelect.setVisibility(View.GONE);
        } else {
            curSelectMan = false;
            man.setTextColor(ContextCompat.getColor(TrueInfoActivity.this, R.color.text_666666));
            woman.setTextColor(ContextCompat.getColor(TrueInfoActivity.this, R.color.main_color));
            manSelect.setVisibility(View.GONE);
            womanSelect.setVisibility(View.VISIBLE);
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TrueInfoActivity.class));
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, TrueInfoActivity.class), REQUEST_CODE);
    }
}

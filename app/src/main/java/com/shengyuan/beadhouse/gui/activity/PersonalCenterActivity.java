package com.shengyuan.beadhouse.gui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.model.LoginBean;

import rx.functions.Action1;

/**
 * 个人中心
 * Created by dell on 2017/11/12.
 */

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout headLay, accountLay, trueInfoLay, bindingPhoneLay, modifyPwdLay, careListLay, inviteControlLay;
    private ImageView icon;
    private TextView account, trueInfo, phone;
    private TextView careNum, inviteControlNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.personal_center));
        headLay = (LinearLayout) findViewById(R.id.personal_head_icon_lay);
        accountLay = (LinearLayout) findViewById(R.id.personal_account_lay);
        trueInfoLay = (LinearLayout) findViewById(R.id.personal_modify_true_name_lay);
        bindingPhoneLay = (LinearLayout) findViewById(R.id.personal_modify_binding_phone_lay);
        modifyPwdLay = (LinearLayout) findViewById(R.id.personal_modify_pwd_lay);
        careListLay = (LinearLayout) findViewById(R.id.personal_care_old_man_lay);
        inviteControlLay = (LinearLayout) findViewById(R.id.personal_invite_control_man_lay);
        icon = (ImageView) findViewById(R.id.personal_center_icon);
        account = (TextView) findViewById(R.id.personal_center_account);
        trueInfo = (TextView) findViewById(R.id.personal_center_true_name);
        phone = (TextView) findViewById(R.id.personal_center_binding_phone);
        careNum = (TextView) findViewById(R.id.personal_care_old_man_num);
        inviteControlNum = (TextView) findViewById(R.id.personal_invite_control_man_num);

        headLay.setOnClickListener(this);
        accountLay.setOnClickListener(this);
        trueInfoLay.setOnClickListener(this);
        bindingPhoneLay.setOnClickListener(this);
        modifyPwdLay.setOnClickListener(this);
        careListLay.setOnClickListener(this);
        inviteControlLay.setOnClickListener(this);

        // 获取本地数据库中保存的个人信息
        getPersonalInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_head_icon_lay:
                // 头像部分
                break;
            case R.id.personal_account_lay:
                // 帐号部分
                break;
            case R.id.personal_modify_true_name_lay:
                // 真实信息部分
                TrueInfoActivity.startActivityForResult(PersonalCenterActivity.this);
                break;
            case R.id.personal_modify_binding_phone_lay:
                // 更改绑定手机部分
                BindPhoneActivity.startActivity(PersonalCenterActivity.this);
                break;
            case R.id.personal_modify_pwd_lay:
                // 更改密码部分
                ModifyPwdActivity.startActivity(PersonalCenterActivity.this, account.getText().toString().trim());
                break;
            case R.id.personal_care_old_man_lay:
                // 关注老人部分
                break;
            case R.id.personal_invite_control_man_lay:
                // 邀请监控人部分
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TrueInfoActivity.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // 完善用户信息成功
                // 再次去获取当前用户的信息
                getPersonalInfo();
            }
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
     * 设置个人信息显示视图
     *
     * @param bean
     */
    private void setInfoView(LoginBean bean) {
        if (bean == null) return;
        GlideLoader.loadNetWorkResource(PersonalCenterActivity.this, bean.getUser().getPhoto(), icon, R.mipmap.personal_default_icon, true);
        account.setText(bean.getUser().getUsername());
        trueInfo.setText(bean.getUser().getName());
        phone.setText(bean.getUser().getUsername());
        // TODO 关注老人的数量、和邀请的监护人数量待定
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PersonalCenterActivity.class));
    }
}

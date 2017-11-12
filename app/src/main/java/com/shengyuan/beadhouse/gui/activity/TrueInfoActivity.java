package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 实名信息界面
 * Created by dell on 2017/11/12.
 */

public class TrueInfoActivity extends BaseActivity implements View.OnClickListener {
    private TextView account;
    private EditText nameInput;
    private TextView sex;
    private EditText cardNumInput;
    private TextView commitBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_true_info;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.true_name));
        account = (TextView) findViewById(R.id.true_info_account_text);
        nameInput = (EditText) findViewById(R.id.true_info_name_input);
        sex = (TextView) findViewById(R.id.true_info_sex_text);
        cardNumInput = (EditText) findViewById(R.id.true_info_card_num_input);
        commitBtn = (TextView) findViewById(R.id.true_info_commit_btn);
        commitBtn.setOnClickListener(this);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.true_info_commit_btn:
                // 提交按钮
                break;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,TrueInfoActivity.class));
    }
}

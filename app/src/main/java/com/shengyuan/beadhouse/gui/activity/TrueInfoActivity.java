package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private TextView man,woman;
    private ImageView manSelect,womanSelect;
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
        man = (TextView) findViewById(R.id.true_info_sex_man);
        man.setOnClickListener(this);
        woman = (TextView) findViewById(R.id.true_info_sex_woman);
        woman.setOnClickListener(this);
        manSelect = (ImageView) findViewById(R.id.true_info_man_select);
        womanSelect = (ImageView) findViewById(R.id.true_info_woman_select);
        cardNumInput = (EditText) findViewById(R.id.true_info_card_num_input);
        commitBtn = (TextView) findViewById(R.id.true_info_commit_btn);
        commitBtn.setOnClickListener(this);

        setSelectSex(0);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.true_info_commit_btn:
                // 提交按钮
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
     * 设置显示性别选择的视图
     * @param sex 0=男，1=女
     */
    private void setSelectSex(int sex){
        if(sex == 0){
            man.setTextColor(ContextCompat.getColor(TrueInfoActivity.this,R.color.main_color));
            woman.setTextColor(ContextCompat.getColor(TrueInfoActivity.this,R.color.text_666666));
            manSelect.setVisibility(View.VISIBLE);
            womanSelect.setVisibility(View.GONE);
        }else {
            man.setTextColor(ContextCompat.getColor(TrueInfoActivity.this,R.color.text_666666));
            woman.setTextColor(ContextCompat.getColor(TrueInfoActivity.this,R.color.main_color));
            manSelect.setVisibility(View.GONE);
            womanSelect.setVisibility(View.VISIBLE);
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,TrueInfoActivity.class));
    }
}

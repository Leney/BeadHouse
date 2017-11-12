package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 修改密码
 * Created by dell on 2017/11/12.
 */

public class ModifyPwdActivity extends BaseActivity implements View.OnClickListener {
    private EditText oldPwdInput,newPwdInput;
    private TextView confirmBtn;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.modify_pwd));
        oldPwdInput = (EditText) findViewById(R.id.modify_pwd_old_pwd_input);
        newPwdInput = (EditText) findViewById(R.id.modify_pwd_new_pwd_input);
        confirmBtn = (TextView) findViewById(R.id.modify_pwd_confirm_btn);
        confirmBtn.setOnClickListener(this);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_pwd_confirm_btn:
                // 确认修改
                String oldPwd = oldPwdInput.getText().toString();
                if(oldPwd.isEmpty()){
                    Toast.makeText(this, getResources().getString(R.string.input_old_pwd), Toast.LENGTH_SHORT).show();
                    return;
                }
                String newPwd = newPwdInput.getText().toString();
                if(newPwd.isEmpty()){
                    Toast.makeText(this, getResources().getString(R.string.input_new_pwd), Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO 提交修改密码数据
                break;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,ModifyPwdActivity.class));
    }
}

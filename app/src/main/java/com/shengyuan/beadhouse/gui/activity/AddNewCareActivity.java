package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.dialog.SelectRelationshipDialog;

/**
 * 新增关注老人界面
 * Created by dell on 2017/11/27.
 */

public class AddNewCareActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout searchLay;
    private ConstraintLayout resultLay;
    private EditText searchInput;

    private ImageView icon;
    private TextView name, age, sex, cardNo;

    private TextView searchAndAddBtn;

    /**
     * 标识当前页是否显示结果页面
     */
    private boolean isResult = false;

    /**
     * 选择关系dialog
     */
    private SelectRelationshipDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_new_care;
    }

    @Override
    protected void initView() {
        searchLay = (LinearLayout) findViewById(R.id.add_new_care_search_lay);
        resultLay = (ConstraintLayout) findViewById(R.id.add_new_care_search_result_lay);
        searchInput = (EditText) findViewById(R.id.add_new_care_card_input);
        icon = (ImageView) findViewById(R.id.add_new_care_search_result_icon);
        name = (TextView) findViewById(R.id.add_new_care_search_result_name);
        age = (TextView) findViewById(R.id.add_new_care_search_result_age);
        sex = (TextView) findViewById(R.id.add_new_care_search_result_sex);
        cardNo = (TextView) findViewById(R.id.add_new_care_search_result_card_no);
        searchAndAddBtn = (TextView) findViewById(R.id.add_new_care_search_now_btn);
        searchAndAddBtn.setOnClickListener(this);
        setVisibleLay();
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new_care_search_now_btn:
                if (isResult) {
                    // 当前是结果页面，点击则添加----> 弹框选择关系
                    if (dialog == null) {
                        dialog = new SelectRelationshipDialog(AddNewCareActivity.this);
                    }
                    dialog.show();
                } else {
                    // 当前页是搜索页面，点击则搜索
                    String no = searchInput.getText().toString();
                    if (no.length() != 18) {
                        Toast.makeText(AddNewCareActivity.this, getResources().getString(R.string.input_right_card_no), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    isResult = true;
                    setVisibleLay();
                }
                break;
        }
    }

    private void setVisibleLay() {
        if (isResult) {
            searchLay.setVisibility(View.GONE);
            resultLay.setVisibility(View.VISIBLE);
            searchAndAddBtn.setText(getResources().getString(R.string.add_care));
        } else {
            searchLay.setVisibility(View.VISIBLE);
            resultLay.setVisibility(View.GONE);
            searchAndAddBtn.setText(getResources().getString(R.string.search_now));
        }
    }

    @Override
    public void onBackPressed() {
        if (isResult) {
            isResult = false;
            setVisibleLay();
            return;
        }
        super.onBackPressed();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AddNewCareActivity.class));
    }
}

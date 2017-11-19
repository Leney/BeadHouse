package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 老人资料详情
 * Created by dell on 2017/11/19.
 */

public class OldManDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView familyPhone,mobilePhone;

    private TextView addressRang,address;

    private ImageView familyPhoneCall,mobilePhoneCall;

    /**
     * 老人id
     */
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_old_man_detail;
    }

    @Override
    protected void initView() {

        id = getIntent().getIntExtra("id",-1);
        if(id < 0){
            finish();
            return;
        }

        baseTitle.setTitleName(getResources().getString(R.string.old_man_detail));

        familyPhone = (TextView) findViewById(R.id.old_man_detail_family_phone);
        mobilePhone = (TextView) findViewById(R.id.old_man_detail_mobile_phone);

        addressRang = (TextView) findViewById(R.id.old_man_detail_address_range);
        address = (TextView) findViewById(R.id.old_man_detail_address);

        familyPhoneCall = (ImageView) findViewById(R.id.old_man_detail_family_phone_call_btn);
        familyPhoneCall.setOnClickListener(this);
        mobilePhoneCall = (ImageView) findViewById(R.id.old_man_detail_mobile_phone_call_btn);
        mobilePhoneCall.setOnClickListener(this);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.old_man_detail_family_phone_call_btn:
                // 家庭固话拨打电话按钮
                break;
            case R.id.old_man_detail_mobile_phone_call_btn:
                // 移动电话拨打电话按钮
                break;
        }
    }


    public static void startActivity(Context context,int id){
        Intent intent = new Intent(context,OldManDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

}

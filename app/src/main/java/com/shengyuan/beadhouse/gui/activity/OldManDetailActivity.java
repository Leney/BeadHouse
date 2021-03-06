package com.shengyuan.beadhouse.gui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.util.StringUtils;

/**
 * 老人资料详情
 * Created by dell on 2017/11/19.
 */

public class OldManDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView icon;

    private TextView name;

    private TextView familyPhone, mobilePhone;

    private TextView addressRang, address;

    private ImageView familyPhoneCall, mobilePhoneCall;

    private CareOldManListBean.FocusListBean bean;

    private Drawable manDrawable, womanDrawable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_old_man_detail;
    }

    @Override
    protected void initView() {

//        id = getIntent().getIntExtra("id",-1);
//        if(id < 0){
//            finish();
//            return;
//        }
        bean = (CareOldManListBean.FocusListBean) getIntent().getSerializableExtra("bean");

        baseTitle.setTitleName(getResources().getString(R.string.old_man_detail));

        manDrawable = ContextCompat.getDrawable(OldManDetailActivity.this, R.mipmap.man);
        manDrawable.setBounds(0, 0, manDrawable.getMinimumWidth(), manDrawable.getMinimumHeight());
        womanDrawable = ContextCompat.getDrawable(OldManDetailActivity.this, R.mipmap.woman);
        womanDrawable.setBounds(0, 0, womanDrawable.getMinimumWidth(), womanDrawable.getMinimumHeight());

        icon = (ImageView) findViewById(R.id.old_man_detail_icon);
        name = (TextView) findViewById(R.id.old_man_detail_name);

        familyPhone = (TextView) findViewById(R.id.old_man_detail_family_phone);
        mobilePhone = (TextView) findViewById(R.id.old_man_detail_mobile_phone);

        addressRang = (TextView) findViewById(R.id.old_man_detail_address_range);
        address = (TextView) findViewById(R.id.old_man_detail_address);

        familyPhoneCall = (ImageView) findViewById(R.id.old_man_detail_family_phone_call_btn);
        familyPhoneCall.setOnClickListener(this);
        mobilePhoneCall = (ImageView) findViewById(R.id.old_man_detail_mobile_phone_call_btn);
        mobilePhoneCall.setOnClickListener(this);

        setInfo();

        showCenterView();
    }

    private void setInfo() {
        GlideLoader.loadNetWorkResource(OldManDetailActivity.this, bean.getPhoto(), icon, true);
        familyPhone.setText(StringUtils.isEmpty(bean.getCell_phone()) ? getResources().getString(R.string.un_setting) : bean.getCell_phone());
        mobilePhone.setText(StringUtils.isEmpty(bean.getFix_phone()) ? getResources().getString(R.string.un_setting) : bean.getFix_phone());
        addressRang.setText(StringUtils.isEmpty(bean.getArea()) ? getResources().getString(R.string.un_setting) : bean.getArea());
        address.setText(StringUtils.isEmpty(bean.getAddress()) ? getResources().getString(R.string.un_setting) : bean.getAddress());
        name.setText(bean.getName() + " " + bean.getAge() + "岁");
        name.setCompoundDrawables(null, null, bean.getSex().equals("男") ? manDrawable : womanDrawable, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.old_man_detail_family_phone_call_btn:
                // 家庭固话拨打电话按钮
                if (bean.getCell_phone().isEmpty()) return;
                call(bean.getCell_phone());
                break;
            case R.id.old_man_detail_mobile_phone_call_btn:
                // 移动电话拨打电话按钮
                if (bean.getFix_phone().isEmpty()) return;
                call(bean.getFix_phone());
                break;
        }
    }

    private void call(String phone) {
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));//直接拨打电话
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(OldManDetailActivity.this, "请到设置中打开拨打电话权限!", Toast.LENGTH_SHORT).show();
            return;
        }
        OldManDetailActivity.this.startActivity(dialIntent);
    }


    public static void startActivity(Context context, CareOldManListBean.FocusListBean bean) {
        Intent intent = new Intent(context, OldManDetailActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }

}

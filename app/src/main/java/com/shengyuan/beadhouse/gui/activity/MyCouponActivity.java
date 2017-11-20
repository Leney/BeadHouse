package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.CouponAdapter;
import com.shengyuan.beadhouse.model.CouponBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的优惠券界面
 * Created by dell on 2017/11/20.
 */

public class MyCouponActivity extends BaseActivity implements View.OnClickListener {
    private EditText codeInput;
    private TextView exchangeBtn;
    private ListView listView;
    private CouponAdapter adapter;
    private List<CouponBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CouponBean bean = new CouponBean();
            bean.id = i;
            bean.money = 100 + (i * 100);
            bean.name = "优惠券名称(" + i + ")";
            bean.exchangeDate = "2017-09-19";
            bean.validDate = "2017-12-19";
            bean.isUsed = i % 2 == 0;
            list.add(bean);
        }

        baseTitle.setTitleName(getResources().getString(R.string.my_coupons));
        codeInput = (EditText) findViewById(R.id.my_coupon_input);
        exchangeBtn = (TextView) findViewById(R.id.my_coupon_exchange_btn);
        exchangeBtn.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.my_coupon_list_view);
        adapter = new CouponAdapter(list);
        listView.setAdapter(adapter);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_coupon_exchange_btn:
                // 兑换
                break;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,MyCouponActivity.class));
    }
}

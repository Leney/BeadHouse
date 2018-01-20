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
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.model.CouponBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.StringUtils;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

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
    private WaitingDialog waitingDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            CouponBean bean = new CouponBean();
//            bean.id = i;
//            bean.money = 100 + (i * 100);
//            bean.name = "优惠券名称(" + i + ")";
//            bean.exchangeDate = "2017-09-19";
//            bean.validDate = "2017-12-19";
//            bean.isUsed = i % 2 == 0;
//            list.add(bean);
//        }

        baseTitle.setTitleName(getResources().getString(R.string.my_coupons));
        codeInput = (EditText) findViewById(R.id.my_coupon_input);
        exchangeBtn = (TextView) findViewById(R.id.my_coupon_exchange_btn);
        exchangeBtn.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.my_coupon_list_view);
        adapter = new CouponAdapter(list);
        listView.setAdapter(adapter);

        showCenterView();

        getMyCouponList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_coupon_exchange_btn:
                // 兑换
                exchangeCoupon();
                break;
        }
    }

    /**
     * 获取我的优惠券列表
     */
    private void getMyCouponList(){
        Subscription subscription = retrofitClient.getMyCouponList(new ResponseResultListener<List<CouponBean>>() {
            @Override
            public void success(List<CouponBean> beanList) {
                list.clear();
                list.addAll(beanList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(CommonException e) {
                ToastUtils.showToast(e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 兑换优惠码
     */
    private void exchangeCoupon(){
        String code = codeInput.getText().toString().trim();
        if(StringUtils.isEmpty(code)){
            ToastUtils.showToast(getResources().getString(R.string.input_code_please));
            return;
        }
        if(waitingDialog == null){
            waitingDialog = new WaitingDialog(MyCouponActivity.this);
        }
        waitingDialog.show();
        Subscription subscription = retrofitClient.exchangeCouponByCode(code, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                waitingDialog.dismiss();
                ToastUtils.showToast(getResources().getString(R.string.exchange_success));
                // 再次去获取我的优惠券列表
                getMyCouponList();
            }

            @Override
            public void failure(CommonException e) {
                waitingDialog.dismiss();
                ToastUtils.showToast(e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,MyCouponActivity.class));
    }
}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.PhysiologyAdapter;
import com.shengyuan.beadhouse.model.PhysicBean;
import com.shengyuan.beadhouse.model.PhysiologyBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * 生理数据界面
 * Created by dell on 2017/11/17.
 */

public class PhysiologyDataActivity extends BaseActivity {
    private ExpandableListView expandableListView;
    private PhysiologyAdapter adapter;
    private List<String> groupList;
    private List<List<PhysiologyBean>> childList;

    /**
     * 分值
     */
    private TextView fraction;
    private ImageView fractionImg;

    /** 老人身份证号*/
    private String cardId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_physiology;
    }

    @Override
    protected void initView() {

        cardId = getIntent().getStringExtra("cardId");
        baseTitle.setTitleName(getResources().getString(R.string.physiology_date));

        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        fraction = (TextView) findViewById(R.id.physiology_fraction);
        fractionImg = (ImageView) findViewById(R.id.physiology_fraction_bg);
        expandableListView = (ExpandableListView) findViewById(R.id.physiology_expandable_list_view);

        getPhysicData(cardId);

    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        getPhysicData(cardId);
    }

    /**
     * 获取生理数据
     *
     * @param cardId 身份证号
     */
    private void getPhysicData(String cardId) {
        Subscription subscription = retrofitClient.getPhysicData(cardId, new ResponseResultListener<PhysicBean>() {
            @Override
            public void success(PhysicBean physicBean) {
                if(physicBean == null || physicBean.getPhysics().isEmpty()){
                    showEmptyView();
                }else {
                    doChangeData(physicBean);
                    showCenterView();
                }
            }

            @Override
            public void failure(CommonException e) {
                showErrorView();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 处理转换数据
     */
    private void doChangeData(PhysicBean bean) {
        int length = bean.getPhysics().size();
        for (int i = 0; i < length; i++) {
            PhysicBean.PhysicsBean item = bean.getPhysics().get(i);
            groupList.add(item.getDate() + " " + item.getDaytime());
            // 体重
            PhysiologyBean weight = new PhysiologyBean();
            weight.name = "体重";
            weight.value = item.getWeight()+"kg";
            // 体温
            PhysiologyBean temperature = new PhysiologyBean();
            temperature.name = "体温";
            temperature.value = item.getTemperature()+"°";
            // 心率
            PhysiologyBean heart_rate = new PhysiologyBean();
            heart_rate.name = "心率";
            heart_rate.value = item.getHeart_rate()+"次/分";
            // 血压
            PhysiologyBean blood_pressure = new PhysiologyBean();
            blood_pressure.name = "血压";
            blood_pressure.value = item.getBlood_pressure()+"mmHg";
            // 血糖
            PhysiologyBean blood_sugar = new PhysiologyBean();
            blood_sugar.name = "血糖";
            blood_sugar.value = item.getBlood_sugar()+"mmol/L";
            // 血脂
            PhysiologyBean blood_fat = new PhysiologyBean();
            blood_fat.name = "血脂";
            blood_fat.value = item.getBlood_fat()+"mmol";

            List<PhysiologyBean> list = new ArrayList<>();
            list.add(weight);
            list.add(temperature);
            list.add(heart_rate);
            list.add(blood_pressure);
            list.add(blood_sugar);
            list.add(blood_fat);
            childList.add(i, list);
        }

        if(length <= 0) return;
        int totalFraction = Integer.parseInt(bean.getPhysics().get(0).getCore());
        fraction.setText(totalFraction + "");
        if (totalFraction < 60) {
            fractionImg.setImageResource(R.mipmap.phy_scroe_red);
        } else if (totalFraction >= 60 && totalFraction <= 80) {
            fractionImg.setImageResource(R.mipmap.phy_scroe_yellow);
        } else {
            fractionImg.setImageResource(R.mipmap.phy_scroe_blue);
        }

        adapter = new PhysiologyAdapter(groupList, childList);
        expandableListView.setAdapter(adapter);

        int count = expandableListView.getCount();
        for (int i = 0; i < count; i++) {
            expandableListView.expandGroup(i);
        }
    }

    public static void startActivity(Context context,String cardId) {
        Intent intent = new Intent(context, PhysiologyDataActivity.class);
        intent.putExtra("cardId",cardId);
        context.startActivity(intent);
    }
}

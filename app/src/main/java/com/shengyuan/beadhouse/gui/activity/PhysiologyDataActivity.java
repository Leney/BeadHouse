package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.PhysiologyAdapter;
import com.shengyuan.beadhouse.model.PhysiologyBean;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_physiology;
    }

    @Override
    protected void initView() {

        baseTitle.setTitleName(getResources().getString(R.string.physiology_date));

        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        groupList.add("2017年10月27日");
        groupList.add("2017年10月26日");
        groupList.add("2017年10月25日");
        groupList.add("2017年10月24日");
        groupList.add("2017年10月23日");

        for (int i = 0; i < 5; i++) {
            List<PhysiologyBean> list = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                PhysiologyBean bean = new PhysiologyBean();
                bean.id = j;
                if (j == 0) {
                    bean.name = "体重";
                    bean.value = (75 + j) + "kg";
                } else if (j == 1) {
                    bean.name = "体温";
                    bean.value = (36 + j) + ".8°";
                } else if (j == 2) {
                    bean.name = "心率";
                    bean.value = (70 + j) + "次/分";
                } else if (j == 3) {
                    bean.name = "血压";
                    bean.value = (120 + j) + "80mmHg";
                } else if (j == 4) {
                    bean.name = "血糖";
                    bean.value = (6 + j) + ".6mmol/L";
                } else if (j == 5) {
                    bean.name = "血脂";
                    bean.value = (160 + j) + "mmol";
                }
                list.add(bean);
            }
            childList.add(list);
        }


        fraction = (TextView) findViewById(R.id.physiology_fraction);
        fractionImg = (ImageView) findViewById(R.id.physiology_fraction_bg);
        int totalFraction = 80;
        fraction.setText(totalFraction + "");
        if (totalFraction < 60) {
            fractionImg.setImageResource(R.mipmap.phy_scroe_red);
        }else if(totalFraction >= 60 && totalFraction <= 80){
            fractionImg.setImageResource(R.mipmap.phy_scroe_yellow);
        }else {
            fractionImg.setImageResource(R.mipmap.phy_scroe_blue);
        }
        expandableListView = (ExpandableListView) findViewById(R.id.physiology_expandable_list_view);
        adapter = new PhysiologyAdapter(groupList, childList);
        expandableListView.setAdapter(adapter);

        int count = expandableListView.getCount();
        for (int i = 0; i < count; i++) {
            expandableListView.expandGroup(i);
        }
        showCenterView();

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PhysiologyDataActivity.class));
    }
}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.GuardianAdapter;
import com.shengyuan.beadhouse.model.GuardianBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 监护人
 * Created by dell on 2017/11/18.
 */

public class GuardianActivity extends BaseActivity {
    private ListView listView;
    private GuardianAdapter adapter;
    private List<GuardianBean> guardianBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guardian_list;
    }

    @Override
    protected void initView() {

        guardianBeanList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            GuardianBean bean = new GuardianBean();
            bean.id = i;
            bean.name = "监护人姓名(" + i + ")";
            bean.phone = "151515165118";
            int v = i % 3;
            if (v == 0) {
                bean.relationship = "子女";
                bean.icon = "http://img1.imgtn.bdimg.com/it/u=410955806,4164577389&fm=27&gp=0.jpg";
            } else if (v == 1){
                bean.relationship = "亲戚";
                bean.icon = "http://www.wzfzl.cn/uploads/allimg/140219/1_140219103511_2.jpg";
            }else {
                bean.relationship = "朋友";
                bean.icon = "http://img1.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=27&gp=0.jpg";
            }
            guardianBeanList.add(bean);
        }

        baseTitle.setTitleName(getResources().getString(R.string.guardian));
        listView = (ListView) findViewById(R.id.guardian_list_view);
        adapter = new GuardianAdapter(guardianBeanList);
        listView.setAdapter(adapter);

        showCenterView();
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,GuardianActivity.class));
    }
}

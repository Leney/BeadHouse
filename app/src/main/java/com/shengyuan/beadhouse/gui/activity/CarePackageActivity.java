package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.CarePackageAdapter;
import com.shengyuan.beadhouse.model.CarePackageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 照护套餐界面
 * Created by dell on 2017/11/19.
 */

public class CarePackageActivity extends BaseActivity {
    private ListView listView;
    private CarePackageAdapter adapter;
    private List<CarePackageBean> list;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_care_package_list;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", -1);
        if (id < 0) {
            finish();
            return;
        }
        baseTitle.setTitleName(getResources().getString(R.string.care_package));
        list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CarePackageBean bean = new CarePackageBean();
            bean.id = i;
            bean.name = "照护套餐名称(" + i + ")";
            bean.info = "1.描述xxxxxxxx\n2.描述yyyyyyyyyyy\n3描述zzzzzzzzzzzzzzzzzzzz";
            bean.beginTime = "2017-10-25";
            bean.endTime = "2017-12-15";
            bean.progress = 30 + (i * 10);
            list.add(bean);
        }


        listView = (ListView) findViewById(R.id.care_package_list_view);
        adapter = new CarePackageAdapter(list);
        listView.setAdapter(adapter);

        showCenterView();
    }

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, CarePackageActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}

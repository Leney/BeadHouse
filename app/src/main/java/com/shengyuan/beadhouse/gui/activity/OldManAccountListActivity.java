package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.OldManAccountAdapter;
import com.shengyuan.beadhouse.model.OldManAccountBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 老人账户列表
 * Created by dell on 2017/11/12.
 */

public class OldManAccountListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private OldManAccountAdapter adapter;
    private List<OldManAccountBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_old_man_account_list;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OldManAccountBean bean = new OldManAccountBean();
            bean.id = i;
            bean.name = "老人名称(" + i + ")";
            bean.icon = "";
            bean.sex = i % 2 == 0 ? 0 : 1;
            bean.age = 80+i;
            list.add(bean);
        }


        baseTitle.setTitleName(getResources().getString(R.string.account_money));
        listView = (ListView) findViewById(R.id.old_man_account_list_view);
        adapter = new OldManAccountAdapter(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        showCenterView();
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,OldManAccountListActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OldManAccountBean bean = list.get(position);
        OldManAccountDetailActivity.startActivity(OldManAccountListActivity.this,bean.id,bean.name);
    }
}

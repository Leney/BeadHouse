package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.CarePackageAdapter;
import com.shengyuan.beadhouse.model.ServicePackageBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 照护套餐界面
 * Created by dell on 2017/11/19.
 */

public class CarePackageActivity extends BaseActivity {
    private ListView listView;
    private CarePackageAdapter adapter;
    private List<ServicePackageBean> list;
    private String cardId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_care_package_list;
    }

    @Override
    protected void initView() {
        cardId = getIntent().getStringExtra("cardId");
        baseTitle.setTitleName(getResources().getString(R.string.care_package));
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.care_package_list_view);
        getServicePackageList(cardId);
    }

    /**
     * 获取服务套餐列表对象
     *
     * @param cardId
     */
    private void getServicePackageList(String cardId) {
        retrofitClient.getServicePagekageList(cardId, new ResponseResultListener<List<ServicePackageBean>>() {
            @Override
            public void success(List<ServicePackageBean> servicePackageBeen) {
                list.addAll(servicePackageBeen);
                if (list.isEmpty()) {
                    showEmptyView();
                } else {
                    adapter = new CarePackageAdapter(list);
                    listView.setAdapter(adapter);
                    showCenterView();
                }
            }

            @Override
            public void failure(CommonException e) {

            }
        });
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        getServicePackageList(cardId);
    }

    public static void startActivity(Context context, String cardId) {
        Intent intent = new Intent(context, CarePackageActivity.class);
        intent.putExtra("cardId", cardId);
        context.startActivity(intent);
    }
}

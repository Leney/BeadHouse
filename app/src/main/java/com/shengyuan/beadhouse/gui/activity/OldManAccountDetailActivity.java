package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.OldManAccountDetailAdapter;
import com.shengyuan.beadhouse.model.ServicePackageBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 老人账户详情
 * Created by dell on 2017/11/12.
 */

public class OldManAccountDetailActivity extends BaseActivity implements OldManAccountDetailAdapter.OnContinueMoneyListener {
    private String cardId;
    private String name;
    private ListView listView;
    private OldManAccountDetailAdapter adapter;
    private List<ServicePackageBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_old_man_account_detail;
    }

    @Override
    protected void initView() {
        cardId = getIntent().getStringExtra("cardId");
        name = getIntent().getStringExtra("name");
        baseTitle.setTitleName(name + getResources().getString(R.string.de_account));

        list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            OldManAccountDetailBean bean = new OldManAccountDetailBean();
//            bean.id = i;
//            bean.name = "套餐名称(" + i + ")";
//            bean.describe = "1.适用于xx伤病的居家老人\n2.两周一次的护士随访\n3.每周末两天早晚各一次上门服务\n4.电动5功能4体智能护理床";
//            bean.validityTime = 955411515;
//            bean.isValid = i % 2 == 0;
//            list.add(bean);
//        }


        listView = (ListView) findViewById(R.id.old_man_account_detail_list_view);
        adapter = new OldManAccountDetailAdapter(list);
        adapter.setContinueMoneyListener(this);
        listView.setAdapter(adapter);
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
                    adapter.notifyDataSetChanged();
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

    public static void startActivity(Context context, String cardId, String name) {
        Intent intent = new Intent(context, OldManAccountDetailActivity.class);
        intent.putExtra("cardId", cardId);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public void onContinueMoney(ServicePackageBean bean) {
        // 续费按钮点击事件
        ToastUtils.showToast("点击续费(" + bean.getTitle() + ")套餐");
    }
}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.GuardianAdapter;
import com.shengyuan.beadhouse.model.GuardianBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;

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
    private String cardId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guardian_list;
    }

    @Override
    protected void initView() {

        cardId = getIntent().getStringExtra("cardId");
        guardianBeanList = new ArrayList<>();

        baseTitle.setTitleName(getResources().getString(R.string.guardian));
        listView = (ListView) findViewById(R.id.guardian_list_view);
        adapter = new GuardianAdapter(guardianBeanList);
        listView.setAdapter(adapter);

        getGuardianList(cardId);
    }

    /**
     * 获取监护人列表
     */
    private void getGuardianList(String cardId) {
        retrofitClient.getGuardianForOldMan(cardId, new ResponseResultListener<List<GuardianBean>>() {
            @Override
            public void success(List<GuardianBean> list) {
                guardianBeanList.addAll(list);
                adapter.notifyDataSetChanged();
                showCenterView();
            }

            @Override
            public void failure(CommonException e) {
                showErrorView();
            }
        });
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        getGuardianList(cardId);
    }

    public static void startActivity(Context context, String cardId) {
        Intent intent = new Intent(context, GuardianActivity.class);
        intent.putExtra("cardId", cardId);
        context.startActivity(intent);
    }
}

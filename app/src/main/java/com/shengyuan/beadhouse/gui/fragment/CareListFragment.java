package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.GridView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.adapter.CareListAdapter;
import com.shengyuan.beadhouse.model.CareListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注老人列表
 * Created by dell on 2017/11/11.
 */

public class CareListFragment extends BaseFragment {
    private GridView gridView;
    private CareListAdapter adapter;
    private List<CareListBean> list;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_list;
    }

    @Override
    protected void initView(View rootView) {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CareListBean bean = new CareListBean();
            bean.icon = "";
            bean.name = "名称("+i+")";
            list.add(bean);
        }
        list.add(new CareListBean());


        gridView = rootView.findViewById(R.id.care_list_fragment_grid_view);
        adapter = new CareListAdapter(list);
        gridView.setAdapter(adapter);
        showCenterView();
    }
}

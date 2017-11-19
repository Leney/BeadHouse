package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.OldManDetailActivity;
import com.shengyuan.beadhouse.gui.adapter.CareListAdapter;
import com.shengyuan.beadhouse.model.CareListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注老人列表
 * Created by dell on 2017/11/11.
 */

public class CareListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
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
            bean.icon = "http://img1.imgtn.bdimg.com/it/u=3875968917,2352913688&fm=200&gp=0.jpg";
            bean.name = "名称("+i+")";
            list.add(bean);
        }
        list.add(new CareListBean());


        gridView = rootView.findViewById(R.id.care_list_fragment_grid_view);
        gridView.setOnItemClickListener(this);
        adapter = new CareListAdapter(list);
        gridView.setAdapter(adapter);
        showCenterView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == list.size() -1){
            // 最后一个item "增加老人"
            return;
        }
        // 正常老人详情跳转
        OldManDetailActivity.startActivity(getActivity(),list.get(position).id);
    }
}

package com.shengyuan.beadhouse.gui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shengyuan.beadhouse.Constance;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.AddNewCareActivity;
import com.shengyuan.beadhouse.gui.adapter.CareListAdapter;
import com.shengyuan.beadhouse.model.CareOldManListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注老人列表
 * Created by dell on 2017/11/11.
 */

public class CareListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private CareListAdapter adapter;
    private List<CareOldManListBean.FocusListBean> list = new ArrayList<>();
    private OnSelectedItemListener listener;

    public static CareListFragment newInstance(List<CareOldManListBean.FocusListBean> list) {
        CareListFragment instance = new CareListFragment();
        if (list != null) {
            instance.list.addAll(list);
        }
        // 添加关注老人按钮
        instance.list.add(new CareOldManListBean.FocusListBean());
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_list;
    }

    @Override
    protected void initView(View rootView) {
        gridView = rootView.findViewById(R.id.care_list_fragment_grid_view);
        gridView.setOnItemClickListener(this);
        adapter = new CareListAdapter(list, getActivity());

        gridView.setAdapter(adapter);

        if (listener != null && list.size() >= 2) {
            // 设置默认选择第一个老人
            this.listener.onSelected(list.get(0));
        }
        showCenterView();

        // 注册添加关注老人成功的广播
        getActivity().registerReceiver(mReceiver, new IntentFilter(Constance.ACTION_CARE_NEW_OLD_MAN));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == list.size() - 1) {
            // 最后一个item "增加老人"
            AddNewCareActivity.startActivity(getActivity());
            return;
        }
        if (listener == null || adapter.getCurSelectedPosition() == position) return;
        listener.onSelected(list.get(position));
        adapter.setCurSelectedPosition(position);
//        // 正常老人详情跳转
//        OldManDetailActivity.startActivity(getActivity(),list.get(position).id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销广播
        getActivity().unregisterReceiver(mReceiver);
    }

    public void setOnSelectedItemListener(OnSelectedItemListener listener) {
        this.listener = listener;
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constance.ACTION_CARE_NEW_OLD_MAN)) {
                // 关注了新的老人广播
                CareOldManListBean.FocusListBean focusListBean = (CareOldManListBean.FocusListBean) intent.getSerializableExtra("FocusListBean");
                if (focusListBean == null) return;
                // 添加到倒数第二个
                int length = list.size();
                if (length <= 1) {
                    // 没有关注的老人
                    list.add(0, focusListBean);
                } else {
                    // 有关注都老人
                    // 添加到倒数第二个位置，因为倒数第一个位置是"添加"item
                    list.add(length - 1, focusListBean);
                }
                adapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 选择了子条目的监听器
     */
    public interface OnSelectedItemListener {
        void onSelected(CareOldManListBean.FocusListBean bean);
    }
}

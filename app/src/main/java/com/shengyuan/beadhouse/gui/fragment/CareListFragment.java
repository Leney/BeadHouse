package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.AdapterView;
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

public class CareListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private CareListAdapter adapter;
    private List<CareListBean> list;
    private OnSelectedItemListener listener;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_list;
    }

    @Override
    protected void initView(View rootView) {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CareListBean bean = new CareListBean();
            if (i == 0) {
                bean.icon = "http://www.taopic.com/uploads/allimg/111210/53178-1112100US3100.jpg";
            } else if (i == 1) {
                bean.icon = "http://img4.imgtn.bdimg.com/it/u=1752075433,3129306283&fm=27&gp=0.jpg";
            } else {
                bean.icon = "http://img1.imgtn.bdimg.com/it/u=3875968917,2352913688&fm=200&gp=0.jpg";
            }
            bean.name = "名称(" + i + ")";
            bean.age = 85 + i;
            bean.familyPhone = "0755-25689362";
            bean.mobilePhone = "13589586985";
            bean.addressRang = "广东省深圳市";
            bean.address = "龙华区大浪东头村56栋";
            bean.sex = i % 2;
            list.add(bean);
        }
        list.add(new CareListBean());


        gridView = rootView.findViewById(R.id.care_list_fragment_grid_view);
        gridView.setOnItemClickListener(this);
        adapter = new CareListAdapter(list);

        gridView.setAdapter(adapter);

        if (listener != null && list.size() >= 2) {
            // 设置默认选择第一个老人
            this.listener.onSelected(list.get(0));
        }
        showCenterView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == list.size() - 1) {
            // 最后一个item "增加老人"
            return;
        }
        if (listener == null || adapter.getCurSelectedPosition() == position) return;
        listener.onSelected(list.get(position));
        adapter.setCurSelectedPosition(position);
//        // 正常老人详情跳转
//        OldManDetailActivity.startActivity(getActivity(),list.get(position).id);
    }

    public void setOnSelectedItemListener(OnSelectedItemListener listener) {
        this.listener = listener;
    }

    /**
     * 选择了子条目的监听器
     */
    public interface OnSelectedItemListener {
        void onSelected(CareListBean bean);
    }
}

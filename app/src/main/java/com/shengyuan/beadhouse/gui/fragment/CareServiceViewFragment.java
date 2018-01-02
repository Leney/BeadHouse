package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.CarePackageActivity;
import com.shengyuan.beadhouse.gui.activity.GuardianActivity;
import com.shengyuan.beadhouse.gui.activity.PhysiologyDataActivity;
import com.shengyuan.beadhouse.gui.activity.RemoteServiceActivity;
import com.shengyuan.beadhouse.gui.adapter.ServiceItemAdapter;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.CareServiceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 照护服务总览Fragment
 * Created by dell on 2017/11/7.
 */

public class CareServiceViewFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private ServiceItemAdapter adapter;
    private List<CareServiceBean> itemList;

    /**
     * 当前选中的老人对象
     */
    public CareOldManListBean.FocusListBean curSelectedBean;

    public static CareServiceViewFragment newInstance(CareOldManListBean.FocusListBean curSelectedBean) {
        CareServiceViewFragment instance = new CareServiceViewFragment();
        instance.curSelectedBean = curSelectedBean;
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_service_view;
    }

    @Override
    protected void initView(View rootView) {

        itemList = new ArrayList<>();
        CareServiceBean serviceBean = new CareServiceBean();
        serviceBean.type = CareServiceBean.TYPE_SERVICE_PACKAGE;
        serviceBean.name = "照护套餐";
        serviceBean.describe = "服务进度50%";
        serviceBean.iconRes = R.mipmap.service_package;

        CareServiceBean serviceBean2 = new CareServiceBean();
        serviceBean2.type = CareServiceBean.TYPE_PHYSIOLOGY_INFO;
        serviceBean2.name = "生理数据";
        serviceBean2.describe = "人工测量";
        serviceBean2.iconRes = R.mipmap.service_phys;

        CareServiceBean serviceBean3 = new CareServiceBean();
        serviceBean3.type = CareServiceBean.TYPE_OUTSIDE_MONITOR;
        serviceBean3.name = "远程看护";
        serviceBean3.describe = "实时监控";
        serviceBean3.iconRes = R.mipmap.service_monitor;

        CareServiceBean serviceBean4 = new CareServiceBean();
        serviceBean4.type = CareServiceBean.TYPE_GUARDIAN;
        serviceBean4.name = "监护人";
        serviceBean4.describe = "已有3名";
        serviceBean4.iconRes = R.mipmap.service_guradian;

        itemList.add(serviceBean);
        itemList.add(serviceBean2);
        itemList.add(serviceBean3);
        itemList.add(serviceBean4);

        gridView = rootView.findViewById(R.id.care_service_grid_view);
        adapter = new ServiceItemAdapter(itemList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        showCenterView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CareServiceBean bean = itemList.get(position);
        switch (bean.type) {
            case CareServiceBean.TYPE_SERVICE_PACKAGE:
                // 照护套餐
                if(curSelectedBean == null){
                    // TODO 弹出去关注老人的提示框
                }else {
                    CarePackageActivity.startActivity(getActivity(), curSelectedBean.getID_number());
                }
                break;
            case CareServiceBean.TYPE_PHYSIOLOGY_INFO:
                // 生理数据
                if(curSelectedBean == null){
                    // TODO 弹出去关注老人的提示框
                }else {
                    PhysiologyDataActivity.startActivity(getActivity(),curSelectedBean.getID_number());
                }
                break;
            case CareServiceBean.TYPE_OUTSIDE_MONITOR:
                // 远程监控
                RemoteServiceActivity.startActivity(getActivity());
                break;
            case CareServiceBean.TYPE_GUARDIAN:
                // 监护人
                GuardianActivity.startActivity(getActivity());
                break;
        }
    }

    /**
     * 设置当前显示的老人信息
     *
     * @param bean
     */
    public void setCurSelectedBean(CareOldManListBean.FocusListBean bean) {
        curSelectedBean = bean;
        if (curSelectedBean == null) {
            // 没有选中的老人(没有关注的老人)
            itemList.get(0).describe = "服务进度0%";
            itemList.get(3).describe = "已有0名";
        } else {
            // 有选中的老人
            if(bean.getPack_progress().isEmpty()){
                itemList.get(0).describe = "服务进度0%";
            }else {
                itemList.get(0).describe = "服务进度" + bean.getPack_progress()+"%";
            }
            itemList.get(3).describe = "已有" + bean.getCount()+ "名";
        }
        adapter.notifyDataSetChanged();
    }
}

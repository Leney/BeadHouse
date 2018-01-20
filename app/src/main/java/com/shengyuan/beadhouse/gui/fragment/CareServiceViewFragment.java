package com.shengyuan.beadhouse.gui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.gui.activity.AddNewCareActivity;
import com.shengyuan.beadhouse.gui.activity.CarePackageActivity;
import com.shengyuan.beadhouse.gui.activity.GuardianActivity;
import com.shengyuan.beadhouse.gui.activity.PhysiologyDataActivity;
import com.shengyuan.beadhouse.gui.activity.RemoteServiceActivity;
import com.shengyuan.beadhouse.gui.activity.TrueInfoActivity;
import com.shengyuan.beadhouse.gui.adapter.ServiceItemAdapter;
import com.shengyuan.beadhouse.gui.dialog.NormalTipsDialog;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.CareServiceBean;
import com.shengyuan.beadhouse.model.LoginBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

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

    /** 未关注老人的提示dialog*/
    private NormalTipsDialog unCareOldManDialog;
    /** 完善个人资料的提示框*/
    private NormalTipsDialog completeInfoDialog;

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
        serviceBean.describe = "服务进度0%";
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
        serviceBean4.describe = "已有0名";
        serviceBean4.iconRes = R.mipmap.service_guradian;

        itemList.add(serviceBean);
        itemList.add(serviceBean2);
        itemList.add(serviceBean3);
        itemList.add(serviceBean4);

        gridView = rootView.findViewById(R.id.care_service_grid_view);
        adapter = new ServiceItemAdapter(itemList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        unCareOldManDialog = new NormalTipsDialog(getActivity());
        unCareOldManDialog.setTips(getResources().getString(R.string.un_care_old_man_dialog_tips));
        unCareOldManDialog.setCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消
                unCareOldManDialog.dismiss();
            }
        });
        unCareOldManDialog.setSure(getResources().getString(R.string.go_care), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 去关注
                unCareOldManDialog.dismiss();
                UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        if(TextUtils.equals("yes",loginBean.getComplete())){
                            // 已经完善了个人资料信息
                            // 跳转到添加搜索老人界面
                            AddNewCareActivity.startActivity(getActivity());
                        }else {
                            // 还没有完善个人资料信息
                            // 弹出个人完善个人资料的提示框
                            completeInfoDialog.show();
                        }
                    }
                });
            }
        });

        completeInfoDialog = new NormalTipsDialog(getActivity());
        completeInfoDialog.setTips(getResources().getString(R.string.complete_info_dialog_tips));
        completeInfoDialog.setCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeInfoDialog.dismiss();
            }
        });
        completeInfoDialog.setSure(getResources().getString(R.string.complete_now), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 立即完善
                TrueInfoActivity.startActivity(getActivity());
            }
        });

        showCenterView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CareServiceBean bean = itemList.get(position);
        switch (bean.type) {
            case CareServiceBean.TYPE_SERVICE_PACKAGE:
                // 照护套餐
                if (curSelectedBean == null) {
                    // 弹出去关注老人的提示框
                    unCareOldManDialog.show();
                } else {
                    CarePackageActivity.startActivity(getActivity(), curSelectedBean.getID_number());
                }
                break;
            case CareServiceBean.TYPE_PHYSIOLOGY_INFO:
                // 生理数据
                if (curSelectedBean == null) {
                    // 弹出去关注老人的提示框
                    unCareOldManDialog.show();
                } else {
                    PhysiologyDataActivity.startActivity(getActivity(), curSelectedBean.getID_number());
                }
                break;
            case CareServiceBean.TYPE_OUTSIDE_MONITOR:
                // 远程监控
                if (curSelectedBean == null) {
                    // 弹出去关注老人的提示框
                    unCareOldManDialog.show();
                } else {
                    RemoteServiceActivity.startActivity(getActivity(),curSelectedBean);
                }
                break;
            case CareServiceBean.TYPE_GUARDIAN:
                // 监护人
                if (curSelectedBean == null) {
                    // 弹出去关注老人的提示框
                    unCareOldManDialog.show();
                } else {
                    GuardianActivity.startActivity(getActivity(), curSelectedBean.getID_number());
                }
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
            if (bean.getPack_progress().isEmpty()) {
                itemList.get(0).describe = "服务进度0%";
            } else {
                itemList.get(0).describe = "服务进度" + bean.getPack_progress() + "%";
            }
            itemList.get(3).describe = "已有" + bean.getCount() + "名";
        }
        adapter.notifyDataSetChanged();
    }
}

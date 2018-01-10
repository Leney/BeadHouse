package com.shengyuan.beadhouse.gui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shengyuan.beadhouse.Constance;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.gui.activity.AddNewCareActivity;
import com.shengyuan.beadhouse.gui.activity.TrueInfoActivity;
import com.shengyuan.beadhouse.gui.adapter.CareListAdapter;
import com.shengyuan.beadhouse.gui.dialog.NormalTipsDialog;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.LoginBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * 关注老人列表
 * Created by dell on 2017/11/11.
 */

public class CareListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private CareListAdapter adapter;
    private List<CareOldManListBean.FocusListBean> list = new ArrayList<>();
    private OnSelectedItemListener listener;
    /** 完善个人资料的提示框*/
    private NormalTipsDialog completeInfoDialog;

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
                        if(completeInfoDialog == null){
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
                        }
                        completeInfoDialog.show();
                    }
                }
            });
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

                UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        // 将关注人数加1并保存到数据库
                        loginBean.setFocus_count(loginBean.getFocus_count() + 1);
                        UserAccountManager.getInstance().update(loginBean, null);
                    }
                });
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

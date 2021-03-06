package com.shengyuan.beadhouse.gui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.FragmentAdapter;
import com.shengyuan.beadhouse.gui.activity.AddNewCareActivity;
import com.shengyuan.beadhouse.gui.activity.MainActivity;
import com.shengyuan.beadhouse.gui.activity.OldManDetailActivity;
import com.shengyuan.beadhouse.gui.activity.TrueInfoActivity;
import com.shengyuan.beadhouse.gui.dialog.NormalTipsDialog;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 关爱老人Fragment
 * Created by dell on 2017/11/5.
 */

public class CareElderlyFragment extends BaseFragment implements CareListFragment.OnSelectedItemListener, View.OnClickListener {
    private TabLayout tabLayout;

    private List<Fragment> fragmentList;

    private FragmentAdapter fragmentAdapter;

    private List<String> tabTitleList;

    private ViewPager viewPager;

    private ImageView icon;
    private TextView name;
    /**
     * 当前选中的老人对象
     */
    private CareOldManListBean.FocusListBean curSelectedBean;

    /**
     * 服务总览Fragment
     */
    private CareServiceViewFragment careServiceViewFragment;

    /**
     * 关注老人列表Fragment
     */
    private CareListFragment careListFragment;

    private View rootView;

    /** 未关注老人的提示dialog*/
    private NormalTipsDialog unCareOldManDialog;

    /** 完善个人资料的提示框*/
    private NormalTipsDialog completeInfoDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_elderly;
    }

    @Override
    protected void initView(View rootView) {
//        showCenterView();

        icon = rootView.findViewById(R.id.care_elderly_user_icon);
        name = rootView.findViewById(R.id.care_elderly_name);
        rootView.findViewById(R.id.care_elderly_info_btn).setOnClickListener(this);
        this.rootView = rootView;
        getCareOldManList();
    }

    private void init(List<CareOldManListBean.FocusListBean> list) {
        fragmentList = new ArrayList<>();
        tabTitleList = new ArrayList<>();

        if (list == null || list.isEmpty()) {
            // 没有关注的老人
            careServiceViewFragment = CareServiceViewFragment.newInstance(null);
        } else {
            curSelectedBean = list.get(0);
            careServiceViewFragment = CareServiceViewFragment.newInstance(curSelectedBean);
        }
        careListFragment = CareListFragment.newInstance(list);
        // 设置老人列表item监听
        careListFragment.setOnSelectedItemListener(this);

        fragmentList.add(careServiceViewFragment);
        fragmentList.add(careListFragment);

        tabTitleList.add(getResources().getString(R.string.care_all_view));
        tabTitleList.add(getResources().getString(R.string.care_elderly_list));

        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentList, tabTitleList);

        tabLayout = rootView.findViewById(R.id.care_elderly_tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = rootView.findViewById(R.id.care_elderly_tab_viewpager);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        showCenterView();

        initDialog();
    }

    private void initDialog(){
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
    }

    @Override
    public void onSelected(CareOldManListBean.FocusListBean bean) {
        // 老人列表选择item 发生改变的监听
        setSelectedBeanView(bean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.care_elderly_info_btn:
                // 老人资料卡
                if (curSelectedBean == null) {
                    // 弹窗先关注老人
                    unCareOldManDialog.show();
                    return;
                }
                OldManDetailActivity.startActivity(getActivity(), curSelectedBean);
                break;
        }
    }

    /**
     * 设置选中的老人信息视图显示
     *
     * @param bean
     */
    private void setSelectedBeanView(CareOldManListBean.FocusListBean bean) {
        curSelectedBean = bean;
        ((MainActivity) getActivity()).changeSelectOldMan(curSelectedBean);
        if (curSelectedBean == null) {
            // 没有关注的老人
            icon.setImageResource(R.mipmap.default_user_icon);
            name.setText(getResources().getString(R.string.care_man_first));
        } else {
            // 有关注的老人
            GlideLoader.loadNetWorkResource(getActivity(), curSelectedBean.getPhoto(), icon, R.mipmap.default_user_icon, true);
            name.setText(curSelectedBean.getName() + " " + curSelectedBean.getAge() + "岁");
        }
        careServiceViewFragment.setCurSelectedBean(curSelectedBean);
    }


    @Override
    protected void tryAgain() {
        super.tryAgain();
        getCareOldManList();
    }

    /**
     * 获取关注老人列表
     */
    private void getCareOldManList() {
        Subscription subscription = retrofitClient.getCareOldManList(new ResponseResultListener<CareOldManListBean>() {
            @Override
            public void success(CareOldManListBean bean) {
                init(bean.getFocus_list());
            }

            @Override
            public void failure(CommonException e) {
                showErrorView();
                ToastUtils.showToast(e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }
}

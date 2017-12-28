package com.shengyuan.beadhouse.gui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.Constance;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.activity.MyCouponActivity;
import com.shengyuan.beadhouse.gui.activity.OldManAccountListActivity;
import com.shengyuan.beadhouse.gui.activity.OrderCenterWebActivity;
import com.shengyuan.beadhouse.gui.activity.PersonalCenterActivity;
import com.shengyuan.beadhouse.gui.activity.SystemSettingActivity;
import com.shengyuan.beadhouse.model.LoginBean;

import rx.functions.Action1;

/**
 * 我的Fragment
 * Created by dell on 2017/11/5.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private ImageView userIcon;
    private TextView name;

    //    private TextView msgNum;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {

        userIcon = rootView.findViewById(R.id.mine_user_icon);
//        msgNum = rootView.findViewById(R.id.mine_msg_num);
        name = rootView.findViewById(R.id.mine_personal_name);

        userIcon.setOnClickListener(this);

        rootView.findViewById(R.id.mine_order_center_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_account_money_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_card_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_system_setting_lay).setOnClickListener(this);


        // 注册用户信息改变的广播
        getActivity().registerReceiver(mReceiver, new IntentFilter(Constance.ACTION_MODIFY_USER_INFO));

        // 获取个人信息
        getPersonalInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_user_icon:
                // 头像
                PersonalCenterActivity.startActivity(getActivity());
                break;
            case R.id.mine_order_center_lay:
                // 订单中心
                OrderCenterWebActivity.startActivity(getActivity(), "http://test.vshare.com/", getResources().getString(R.string.order_center));
                break;
            case R.id.mine_account_money_lay:
                // 老人账户
                OldManAccountListActivity.startActivity(getActivity());
                break;
            case R.id.mine_card_lay:
                // 我的优惠券
                MyCouponActivity.startActivity(getActivity());
                break;
            case R.id.mine_system_setting_lay:
                // 系统设置
                SystemSettingActivity.startActivity(getActivity());
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);
    }

    /**
     * 获取当前登陆的个人信息
     */
    private void getPersonalInfo() {
        UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean bean) {
                // 设置个人信息显示
                setInfoView(bean);
                showCenterView();
            }
        });
    }

    /**
     * 设置个人信息显示视图
     *
     * @param bean
     */
    private void setInfoView(LoginBean bean) {
        if (bean == null) return;
        GlideLoader.loadNetWorkResource(getActivity(), bean.getUser().getPhoto(), userIcon, R.mipmap.default_user_icon, true);
        name.setText(bean.getUser().getName());
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constance.ACTION_MODIFY_USER_INFO)) {
                // 用户信息改变广播
                // 重新去获取用户信息数据并显示
                getPersonalInfo();
            }
        }
    };
}

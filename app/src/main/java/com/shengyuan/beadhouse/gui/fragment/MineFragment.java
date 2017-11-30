package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;
import com.shengyuan.beadhouse.gui.activity.LoginActivity;
import com.shengyuan.beadhouse.gui.activity.MyCouponActivity;
import com.shengyuan.beadhouse.gui.activity.OldManAccountListActivity;
import com.shengyuan.beadhouse.gui.activity.OrderCenterWebActivity;
import com.shengyuan.beadhouse.gui.activity.PersonalCenterActivity;
import com.shengyuan.beadhouse.gui.activity.SystemSettingActivity;

/**
 * 照护计划Fragment
 * Created by dell on 2017/11/5.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private ImageView userIcon;
//    private TextView msgNum;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {

        userIcon = rootView.findViewById(R.id.mine_user_icon);
//        msgNum = rootView.findViewById(R.id.mine_msg_num);

        userIcon.setOnClickListener(this);

        rootView.findViewById(R.id.mine_order_center_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_account_money_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_card_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_system_setting_lay).setOnClickListener(this);

        rootView.findViewById(R.id.mine_personal_name).setOnClickListener(this);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_user_icon:
                // 头像
                PersonalCenterActivity.startActivity(getActivity());
                break;
            case R.id.mine_order_center_lay:
                // 订单中心
                OrderCenterWebActivity.startActivity(getActivity(),"http://test.vshare.com/",getResources().getString(R.string.order_center));
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
            case R.id.mine_personal_name:
                // TODO 暂时做登录入口
                LoginActivity.startActivity(getActivity());
                break;
        }
    }
}

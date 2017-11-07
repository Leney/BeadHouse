package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;

/**
 * 照护计划Fragment
 * Created by dell on 2017/11/5.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private ImageView userIcon;
    private TextView msgNum;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {

        userIcon = rootView.findViewById(R.id.mine_user_icon);
        msgNum = rootView.findViewById(R.id.mine_msg_num);

        rootView.findViewById(R.id.mine_order_center_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_account_money_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_card_lay).setOnClickListener(this);
        rootView.findViewById(R.id.mine_system_setting_lay).setOnClickListener(this);

        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_order_center_lay:
                // 订单中心
                break;
            case R.id.mine_account_money_lay:
                // 老人账户
                break;
            case R.id.mine_card_lay:
                // 我的优惠券
                break;
            case R.id.mine_system_setting_lay:
                // 系统设置
                break;
        }
    }
}

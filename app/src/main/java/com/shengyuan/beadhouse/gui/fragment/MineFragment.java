package com.shengyuan.beadhouse.gui.fragment;

import android.view.View;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseFragment;

/**
 * 照护计划Fragment
 * Created by dell on 2017/11/5.
 */

public class MineFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        showCenterView();
    }
}

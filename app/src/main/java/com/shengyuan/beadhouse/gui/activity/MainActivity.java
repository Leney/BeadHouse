package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.gui.fragment.CareElderlyFragment;
import com.shengyuan.beadhouse.gui.fragment.LookAfterPlanFragment;
import com.shengyuan.beadhouse.gui.fragment.MineFragment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentManager fragmentManager = null;

    /**
     * 当前所在tab的标识值
     */
    private int curTab = -1;

    private RadioGroup radioGroup;

    /**
     * 3个tab的fragment
     */
    private Fragment[] fragments = new Fragment[3];

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this,R.color.title_color));

        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.main_tab_radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        // 设置进入应用时 默认跳转的tab
        changePage(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.main_tab_1:
                if (curTab == 0) return;
                changePage(0);
                break;
            case R.id.main_tab_2:
                if (curTab == 1) return;
                changePage(1);
                radioGroup.setVisibility(View.INVISIBLE);
                break;
            case R.id.main_tab_3:
                if(curTab == 2) return;
                changePage(2);
                break;
        }
        radioGroup.check(checkedId);
    }

//    private void changePage(Fragment fragment) {
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.hide(fragments[curTab]);
//        if (!fragment.isAdded()) {
//            // 如果没有被添加过
//            transaction.add(R.id.main_tab_fragment_lay, fragment);
//        } else if (fragment.isHidden()) {
//            transaction.show(fragment);
//        }
//        transaction.commitAllowingStateLoss();
//    }

    private void changePage(int position){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(curTab != -1){
            // 隐藏上一个Fragment
            transaction.hide(fragments[curTab]);
        }
        if(fragments[position] == null){
            createFragment(position);
            transaction.add(R.id.main_tab_fragment_lay,fragments[position]);
        }else {
            transaction.show(fragments[position]);
        }
        curTab = position;
        transaction.commitAllowingStateLoss();
    }

    private void createFragment(int position){
        switch (position){
            case 0:
                // 创建第一个Fragment
                fragments[position] = new CareElderlyFragment();
                break;
            case 1:
                // 创建第二个Fragment
                fragments[position] = new LookAfterPlanFragment();
                break;
            case 2:
                // 创建第三个Fragment
                fragments[position] = new MineFragment();
                break;
        }
    }


//    /**
//     * 还原RadioGroup Tab切换
//     */
//    private void changeTabBySth() {
//        switch (curTab) {
//            case 0:
//                radioGroup.check(R.id.main_tab_1);
//                break;
//            case 1:
//                radioGroup.check(R.id.main_tab_2);
//                break;
//            case 2:
//                radioGroup.check(R.id.main_tab_3);
//                break;
//        }
//    }

//    /**
//     * 切换到指定tab
//     *
//     * @param position
//     */
//    public void changeTab(int position) {
//        curTab = position;
//        changeTabBySth();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int length = fragments.length;
        for (int i = 0; i < length; i++) {
            fragments[i].onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this,
                    getResources().getString(R.string.again_sure_exit),
                    Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
//            NetManager.getInstance().cancleRequests(NetManager.TAG);
            finish();
            System.exit(0);
        }
    }

    public static void startActivity(Context context) {
        // 跳转到主页
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}

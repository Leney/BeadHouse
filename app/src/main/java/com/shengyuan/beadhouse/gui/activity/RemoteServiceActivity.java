package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.map.MapView;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 远程看护界面
 * Created by llj on 2017/12/25.
 */

public class RemoteServiceActivity extends BaseActivity {
    private MapView mapView = null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_remote_service;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.remote_service));
        mapView = (MapView) findViewById(R.id.service_map_view);
        showCenterView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,RemoteServiceActivity.class));
    }
}

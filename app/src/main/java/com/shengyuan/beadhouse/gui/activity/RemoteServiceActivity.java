package com.shengyuan.beadhouse.gui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.easyPermission.PermissionManager;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.view.WrapNormalPlayer;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.LocationAndHeartRateBean;
import com.shengyuan.beadhouse.model.RemoteServiceBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.DisplayUtils;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.List;

import rx.Subscription;

/**
 * 远程看护界面
 * Created by llj on 2017/12/25.
 */

public class RemoteServiceActivity extends BaseActivity implements View.OnClickListener {
    private MapView mapView = null;
    /**
     * 缩略图显示视图
     */
    private LinearLayout viewPointLay;
    private WrapNormalPlayer webPlayer;
    private TextView heartRemoteInfo,contactsBtn;
    private CareOldManListBean.FocusListBean focusListBean;
    /**
     * 当前选中的预览视图position
     */
    private int selectPosition = 0;

    private BaiduMap baiduMap = null;

    private MyLocationData locData = null;

    /** 当前纬度和经度*/
    private double curLatitude,curLongitude;

    /**
     * 拨打电话权限
     */
    private final String[] callPhonePermission = {Manifest.permission.CALL_PHONE};

    private final int REQUEST_LOCATION_INFO_CODE = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == REQUEST_LOCATION_INFO_CODE){
                getLocationAndHeartRateInfo(focusListBean.getID_number());
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remote_service;
    }

    @Override
    protected void initView() {
        focusListBean = (CareOldManListBean.FocusListBean) getIntent().getSerializableExtra("focusListBean");
        baseTitle.setTitleName(getResources().getString(R.string.remote_service));
        mapView = (MapView) findViewById(R.id.service_map_view);
        baiduMap = mapView.getMap();
        webPlayer = (WrapNormalPlayer) findViewById(R.id.remote_service_video_player);
        viewPointLay = (LinearLayout) findViewById(R.id.remote_service_view_lay);
        heartRemoteInfo = (TextView) findViewById(R.id.remote_heart_remote_info);
        contactsBtn = (TextView) findViewById(R.id.remote_contacts_old_man_btn);
        contactsBtn.setOnClickListener(this);
        initPlayer();
        getNurse(focusListBean.getID_number());
//        showCenterView();

//        initLocationMap(22.55724716, 113.93560028);
    }

    /**
     * 初始化
     *
     * @param latitude  纬度
     * @param longitude 经度
     */
    private void initLocationMap(double latitude, double longitude) {
        curLatitude = latitude;
        curLongitude = longitude;
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        locData = new MyLocationData.Builder()
                .accuracy(90)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(curLatitude)
                .longitude(curLongitude).build();

        // 设置定位数据
        baiduMap.setMyLocationData(locData);

//        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.drawable.icon_geo);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
        baiduMap.setMyLocationConfiguration(config);

//// 当不需要定位图层时关闭定位图层
//        baiduMap.setMyLocationEnabled(false);
    }

    private void refreshLocation(double latitude, double longitude){
        curLatitude = latitude;
        curLongitude = longitude;
        // 构造定位数据
        locData = new MyLocationData.Builder()
                .accuracy(90)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(curLatitude)
                .longitude(curLongitude).build();
        // 设置定位数据
        baiduMap.setMyLocationData(locData);
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
        handler.removeMessages(REQUEST_LOCATION_INFO_CODE);
    }

    /**
     * 初始化播放器
     */
    public void initPlayer() {
//        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";

//        webPlayer.setUp(videoUrl, false, null, "");
//        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
////        imageView.setImageResource(R.mipmap.default_lis_head_icon);
//        GlideLoader.loadNetWorkResource(RemoteServiceActivity.this,thumbUrl,imageView,false);
//        webPlayer.setThumbImageView(imageView);

        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getFullscreenButton().setVisibility(View.GONE);
//        resolveNormalVideoUI();
//        //外部辅助的旋转，帮助全屏
//        orientationUtils = new OrientationUtils(this, webPlayer);
//        //初始化不打开外部的旋转
//        orientationUtils.setEnable(false);
        webPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        webPlayer.setRotateViewAuto(false);
        webPlayer.setLockLand(false);
        webPlayer.setShowFullAnimation(false);
        webPlayer.setNeedLockFull(true);
//        webPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                LogUtil.e(TAG, "执行横屏操作");
//                //直接横屏
//                orientationUtils.resolveByClick();
//
//                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
//                webPlayer.startWindowFullscreen(SessionActivity2.this, true, true);
//            }
//        });

//        webPlayer.setStandardVideoAllCallBack(new SampleListener() {
//            @Override
//            public void onPrepared(String url, Object... objects) {
//                super.onPrepared(url, objects);
//                //开始播放了才能旋转和全屏
//                orientationUtils.setEnable(true);
//                isPlay = true;
//            }
//
//            @Override
//            public void onAutoComplete(String url, Object... objects) {
//                super.onAutoComplete(url, objects);
//            }
//
//            @Override
//            public void onClickStartError(String url, Object... objects) {
//                super.onClickStartError(url, objects);
//            }
//
//            @Override
//            public void onQuitFullscreen(String url, Object... objects) {
//                super.onQuitFullscreen(url, objects);
//                if (orientationUtils != null) {
//                    orientationUtils.backToProtVideo();
//                }
//            }
//        });

//        webPlayer.setLockClickListener(new LockClickListener() {
//            @Override
//            public void onClick(View view, boolean lock) {
//                if (orientationUtils != null) {
//                    //配合下方的onConfigurationChanged
//                    orientationUtils.setEnable(!lock);
//                }
//            }
//        });
    }

    /**
     * 获取远程看护信息
     */
    private void getNurse(String cardId) {
        Subscription subscription = retrofitClient.getNurse(cardId, new ResponseResultListener<List<RemoteServiceBean>>() {
            @Override
            public void success(List<RemoteServiceBean> beanList) {
                View view = View.inflate(RemoteServiceActivity.this, R.layout.item_remote_servie_point, null);
                ImageView imageView = view.findViewById(R.id.item_remote_icon);
                imageView.setImageResource(R.mipmap.map_icon);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setBackgroundResource(R.drawable.shape_green_stoken_bg);
                selectPosition = 0;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 切换成地图
                        mapView.setVisibility(View.VISIBLE);
                        webPlayer.setVisibility(View.GONE);
                        webPlayer.onVideoPause();
                        webPlayer.onVideoReset();
                        webPlayer.release();
//                        view.setSelected(true);
                        viewPointLay.getChildAt(selectPosition).setBackgroundResource(R.drawable.shape_gray_stoken_bg2);
                        view.setBackgroundResource(R.drawable.shape_green_stoken_bg);
                        selectPosition = 0;
                    }
                });
                viewPointLay.addView(view);

                LinearLayout.LayoutParams params = null;

                int length = beanList.size();
                for (int i = 0; i < length; i++) {
                    View view2 = View.inflate(RemoteServiceActivity.this, R.layout.item_remote_servie_point, null);
                    ImageView imageView2 = view2.findViewById(R.id.item_remote_icon);
                    GlideLoader.loadNetWorkResource(RemoteServiceActivity.this, beanList.get(i).getPic_path(), imageView2, false);
                    imageView2.setTag(R.id.remote_service_view_lay, beanList.get(i));
                    imageView2.setTag(R.id.item_remote_icon, i);
                    imageView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemoteServiceBean bean = (RemoteServiceBean) v.getTag(R.id.remote_service_view_lay);
                            int position = (int) v.getTag(R.id.item_remote_icon);
                            mapView.setVisibility(View.GONE);
                            webPlayer.setVisibility(View.VISIBLE);
                            webPlayer.setUp(bean.getM3u8_url(), false, "");
//                            webPlayer.setUp("http://221.228.226.23/11/t/j/v/b/tjvbwspwhqdmgouolposcsfafpedmb/sh.yinyuetai.com/691201536EE4912BF7E4F1E2C67B8119.mp4", false, "");
                            webPlayer.startPlay();
                            viewPointLay.getChildAt(selectPosition).setBackgroundResource(R.drawable.shape_gray_stoken_bg2);
                            view2.setBackgroundResource(R.drawable.shape_green_stoken_bg);
                            selectPosition = position + 1;
                        }
                    });
                    if (params == null) {
                        params = new LinearLayout.LayoutParams(imageView2.getLayoutParams());
                        params.leftMargin = DisplayUtils.dip2px(RemoteServiceActivity.this, getResources().getDimension(R.dimen.space_10));
                    }
                    view2.setLayoutParams(params);
                    viewPointLay.addView(view2);
                }

                showCenterView();

                // 获取定位信息
                getLocationAndHeartRateInfo(focusListBean.getID_number());
            }

            @Override
            public void failure(CommonException e) {
                ToastUtils.showToast(e.getErrorMsg());
                showErrorView();
            }
        });

        compositeSubscription.add(subscription);
    }

    /**
     * 获取定位和心率信息
     */
    private void getLocationAndHeartRateInfo(String cardId){
        Subscription subscription = retrofitClient.getLocationAndHeartRateInfo(cardId, new ResponseResultListener<LocationAndHeartRateBean>() {
            @Override
            public void success(LocationAndHeartRateBean locationAndHeartRateBean) {
                double lat = Double.parseDouble(locationAndHeartRateBean.getPosition().getLat());
                double lon = Double.parseDouble(locationAndHeartRateBean.getPosition().getLon());
                if(locData == null){
                    initLocationMap(lat,lon);
                }else {
                    if(curLatitude != lat || curLongitude != lon) {
                        // 如果和上次的坐标不一样才刷新
                        refreshLocation(lat,lon);
                    }
                }
                heartRemoteInfo.setText(locationAndHeartRateBean.getHeart_rate());
                // 30秒之后再次请求
                handler.sendEmptyMessageDelayed(REQUEST_LOCATION_INFO_CODE,30*1000);
            }

            @Override
            public void failure(CommonException e) {
                ToastUtils.showToast(e.getErrorMsg());
                // 30秒之后再次请求
                handler.sendEmptyMessageDelayed(REQUEST_LOCATION_INFO_CODE,30*1000);
            }
        });
        compositeSubscription.add(subscription);
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        getNurse(focusListBean.getID_number());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remote_contacts_old_man_btn:
                // 联系老人
                if (PermissionManager.checkPermission(RemoteServiceActivity.this, callPhonePermission)) {
                    String phone = "";
                    phone = TextUtils.isEmpty(focusListBean.getCell_phone()) ? focusListBean.getFix_phone() : focusListBean.getCell_phone();
                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));//直接拨打电话
                    RemoteServiceActivity.this.startActivity(dialIntent);
                } else {
                    ToastUtils.showToast("请到设置中打开拨打电话权限");
                }
                break;
        }
    }

    public static void startActivity(Context context, CareOldManListBean.FocusListBean focusListBean) {
        Intent intent = new Intent(context, RemoteServiceActivity.class);
        intent.putExtra("focusListBean", focusListBean);
        context.startActivity(intent);
    }
}

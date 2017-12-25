package com.shengyuan.beadhouse;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by dell on 2017/11/2.
 */

public class BHApplication extends Application {
    //    private OkHttpClient okHttpClient;
    private static BHApplication instance;
    private String token = "";
    private static Context mContext;//上下文

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化百度地图
        SDKInitializer.initialize(getApplicationContext());

        // 初始化日志
        Logger.addLogAdapter(new AndroidLogAdapter());
//        Logger.addLogAdapter(new AndroidLogAdapter(){
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
//            }
//        });
    }

//    public OkHttpClient getOkHttpClient() {
//        if (okHttpClient == null) {
//            okHttpClient = new OkHttpClient();
//        }
//        return okHttpClient;
//    }

    /**
     * 单例模式
     *
     * @return
     */
    public synchronized static BHApplication getInstance() {
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        BHApplication.mContext = mContext;
    }
}

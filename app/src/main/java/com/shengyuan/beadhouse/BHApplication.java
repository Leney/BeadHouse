package com.shengyuan.beadhouse;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import okhttp3.OkHttpClient;

/**
 * Created by dell on 2017/11/2.
 */

public class BHApplication extends Application {
    private OkHttpClient okHttpClient;
    private static BHApplication instance;

    public static BHApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // 初始化日志
        Logger.addLogAdapter(new AndroidLogAdapter());
//        Logger.addLogAdapter(new AndroidLogAdapter(){
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
//            }
//        });
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }
}

package com.shengyuan.beadhouse;

import android.app.Application;

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
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }
}

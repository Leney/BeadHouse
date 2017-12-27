package com.shengyuan.beadhouse.okhttp;


import android.text.TextUtils;
import android.util.Log;

import com.shengyuan.beadhouse.BHApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${xingen} on 2017/11/6.
 * 定义一个请求的拦截器：
 * <p>
 * 添加一些共同的header表头:
 * 例如:
 * 数据格式，token,cockie等
 */

public class HeaderInterceptor implements Interceptor {

    /**
     * 常见的表单form格式
     */
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder()
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("User-Agent", "android");
      /*          .addHeader("Accept", "application/vnd.yourapi.v1.full+json")*/
        String token = BHApplication.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("Authorization", token);
        }
        Log.i("llj","header中，token----->>>"+token);
        return chain.proceed(builder.build());
    }
}

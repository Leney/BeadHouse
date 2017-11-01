package com.shengyuan.beadhouse.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by dell on 2017/11/1.
 */

public class NetUtil {

    /**
     * 获取一个get方式请求的url
     * @param mainUrl
     * @param params
     * @return
     */
    public static String getURL(String mainUrl, Map<String, Object> params) {
        StringBuffer sb = new StringBuffer(mainUrl);
        if (params != null && !params.isEmpty() && params.size() > 0) {
            sb.append("?");
            int length = 1;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                if (length != params.size()) {
                    sb.append("&");
                }
                length++;
            }
        }
//        String url = sb.toString();
        String url = "";
        try {
            url = new String(sb.toString().getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 组合okhttp3 post请求参数
     * @param objectMap
     * @return
     */
    public static FormBody.Builder getPostBuilder(Map<String,Object> objectMap){
        if(objectMap == null) return null;
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry entry: objectMap.entrySet()) {
            builder.add(entry.getKey().toString(),entry.getValue().toString());
        }
        return builder;
    }
}

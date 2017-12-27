package com.shengyuan.beadhouse.util;

import android.widget.Toast;

import com.shengyuan.beadhouse.BHApplication;

/**
 * Created by llj on 2017/12/27.
 */

public class ToastUtils {
    public static void showToast(String msg) {
        Toast.makeText(BHApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

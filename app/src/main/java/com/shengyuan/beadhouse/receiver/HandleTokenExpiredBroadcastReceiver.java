package com.shengyuan.beadhouse.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shengyuan.beadhouse.gui.activity.HandleTokenExpiredActivity;
import com.shengyuan.beadhouse.util.ActivityUtils;


/**
 * Created by ${xingen} on 2017/11/8.
 *
 * 处理Token过期的广播
 */

public class HandleTokenExpiredBroadcastReceiver extends BroadcastReceiver {


    public static Intent builderIntent(){
        Intent intent=new Intent();
        intent.setAction("com.zhongke.weiduo.mvp.broadcastreceiver.HandleTokenExpiredBroadcastReceiver");
        return intent;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        handleTokenExpired(context);
    }

    /**
     * @param context
     */
    private void handleTokenExpired(Context context){
        ActivityUtils.openActivity(context,HandleTokenExpiredActivity.class);
    }
}

package com.shengyuan.beadhouse.gui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

/**
 * 封装一层视频播放器
 * Created by dell on 2018/1/10.
 */
public class WrapNormalPlayer extends NormalGSYVideoPlayer {
    public WrapNormalPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public WrapNormalPlayer(Context context) {
        super(context);
    }

    public WrapNormalPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 开始播放时间
     */
    public void startPlay(){
        clickStartIcon();
    }
}

package com.shengyuan.beadhouse.gui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 倒计时控件
 * Created by lilijun on 2016/9/8.
 */
public class CountDownTextView extends TextView {
    private final int COUNT_DOWN_MSG = 1;
    /**
     * 倒计时总次数
     */
    private int totalCount = 60;

    /**
     * 是否正在倒计时
     */
    private boolean isCountDowning = false;

    /**
     * 倒计时开始之前的文本
     */
    private String defaulText;

    private OnCountDownDoneListener listener;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == COUNT_DOWN_MSG) {
                if (--totalCount <= 0) {
                    // 倒计时完成
                    stop();
                    if (listener != null) {
                        listener.onCountDownDone();
                    }
                } else {
                    // 倒计时未完成
                    setText(totalCount + "s");
                    sendEmptyMessageDelayed(COUNT_DOWN_MSG, 1000);
                }
            }
        }
    };

    public CountDownTextView(Context context) {
        super(context);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置监听倒计时完成广播
     *
     * @param listener
     */
    public void setOnDoneListener(OnCountDownDoneListener listener) {
        this.listener = listener;
    }

    // 开始倒计时
    public void start(int totalCount) {
        if(isCountDowning) return;
        this.isCountDowning = true;
        this.totalCount = totalCount;
        this.defaulText = getText().toString();
        setText(totalCount + "s");
        handler.sendEmptyMessageDelayed(COUNT_DOWN_MSG, 1000);
    }

    public void stop() {
        if(!isCountDowning) return;
        this.isCountDowning = false;
        setText(defaulText);
        handler.removeMessages(COUNT_DOWN_MSG);
    }

    /**
     * 倒计时完成接口
     */
    public interface OnCountDownDoneListener {
        void onCountDownDone();
    }
}

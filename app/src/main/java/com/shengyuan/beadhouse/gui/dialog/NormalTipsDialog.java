package com.shengyuan.beadhouse.gui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;

/**
 * 大部分通用的dialog
 * Created by llj on 2017/12/25.
 */

public class NormalTipsDialog extends BaseDialog {
    private TextView tips;
    private TextView cancelBtn,sureBtn;
    public NormalTipsDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public NormalTipsDialog(@NonNull Context context, int style, View.OnClickListener onClickListener) {
        super(context, style, onClickListener);
        init();
    }

    public NormalTipsDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, onClickListener);
        init();
    }

    private void init() {
        setDialogWidth(0.8f);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected View getRootView() {
        return View.inflate(context, R.layout.dialog_nomal_tips, null);
    }

    @Override
    protected void initView(View rootView) {
        tips = rootView.findViewById(R.id.normal_tips_text);
        cancelBtn = rootView.findViewById(R.id.normal_tips_cancel_btn);
        sureBtn = rootView.findViewById(R.id.normal_tips_sure_btn);
    }

    public void setTips(String tips){
        this.tips.setText(tips);
    }

    public void setCancel(String text, View.OnClickListener listener){
        this.cancelBtn.setText(text);
        this.cancelBtn.setOnClickListener(listener);
    }

    public void setSure(String text, View.OnClickListener listener){
        this.sureBtn.setText(text);
        this.sureBtn.setOnClickListener(listener);
    }
}

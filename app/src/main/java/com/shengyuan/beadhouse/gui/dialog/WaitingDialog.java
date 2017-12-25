package com.shengyuan.beadhouse.gui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.shengyuan.beadhouse.R;

/**
 * 请稍候dialog
 * Created by llj on 2017/12/25.
 */

public class WaitingDialog extends BaseDialog {
    public WaitingDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public WaitingDialog(@NonNull Context context, int style, View.OnClickListener onClickListener) {
        super(context, style, onClickListener);
        init();
    }

    public WaitingDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, onClickListener);
        init();
    }

    private void init() {
        setDialogWidth(0.8f);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected View getRootView() {
        return View.inflate(context, R.layout.dialog_toast, null);
    }

    @Override
    protected void initView(View rootView) {

    }
}

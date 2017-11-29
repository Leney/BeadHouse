package com.shengyuan.beadhouse.gui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.gui.adapter.SpinnerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 选择关系dialog
 * Created by dell on 2017/11/29.
 */

public class SelectRelationshipDialog extends BaseDialog implements View.OnClickListener {
    private Spinner spinner;
    private TextView addBtn;

    private SpinnerAdapter spinnerAdapter;

    private List<String> list;

    public SelectRelationshipDialog(@NonNull Context context) {
        super(context);
        setDialogWidth(0.8f);
    }

    @Override
    protected View getRootView() {
        return View.inflate(getContext(), R.layout.dialog_select_relationship, null);
    }

    @Override
    protected void initView(View rootView) {
        setCanceledOnTouchOutside(true);
        spinner = rootView.findViewById(R.id.relationship_dialog_spinner);
        addBtn = rootView.findViewById(R.id.relationship_dialog_sure_add_btn);
        addBtn.setOnClickListener(this);

        String[] array = getContext().getResources().getStringArray(R.array.relationships);
        list = Arrays.asList(array);
        //适配器
        spinnerAdapter= new SpinnerAdapter(list);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relationship_dialog_sure_add_btn:
                dismiss();
                break;
        }
    }
}

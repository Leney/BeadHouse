package com.shengyuan.beadhouse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.gui.view.BaseTitleView;

/**
 * Created by dell on 2017/10/30.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected RelativeLayout loadingLay;
    protected RelativeLayout errorLay;
    protected RelativeLayout emptyLay;
    protected RelativeLayout contentLay;
    protected BaseTitleView baseTitle;

    private RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        baseTitle = (BaseTitleView) findViewById(R.id.base_activity_title_view);
        loadingLay = (RelativeLayout) findViewById(R.id.base_activity_loading_lay);
        errorLay = (RelativeLayout) findViewById(R.id.base_activity_error_lay);
        emptyLay = (RelativeLayout) findViewById(R.id.base_activity_empty_lay);
        contentLay = (RelativeLayout) findViewById(R.id.base_activity_content_lay);

        contentLay.addView(View.inflate(BaseActivity.this, getInitLayout(), null), mParams);
        initView();
    }

    protected abstract int getInitLayout();

    protected abstract void initView();


    protected void showCenterView(){
        loadingLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.GONE);
        emptyLay.setVisibility(View.GONE);
        contentLay.setVisibility(View.VISIBLE);
    }

}

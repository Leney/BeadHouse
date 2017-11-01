package com.shengyuan.beadhouse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.gui.view.BaseTitleView;
import com.shengyuan.beadhouse.util.Tools;

import java.util.Map;

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

    /**
     * 是否设置状态栏颜色
     */
    protected boolean isSetStatusColor = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (isSetStatusColor) {
            // 设置状态栏颜色
            Tools.setStatusColor(this, ContextCompat.getColor(BaseActivity.this, R.color.title_color));
        }

        baseTitle = (BaseTitleView) findViewById(R.id.base_activity_title_view);
        loadingLay = (RelativeLayout) findViewById(R.id.base_activity_loading_lay);
        errorLay = (RelativeLayout) findViewById(R.id.base_activity_error_lay);
        emptyLay = (RelativeLayout) findViewById(R.id.base_activity_empty_lay);
        contentLay = (RelativeLayout) findViewById(R.id.base_activity_content_lay);

        contentLay.addView(View.inflate(BaseActivity.this, getLayoutId(), null), mParams);
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * 加载网络数据
     */
    protected void loadNetDataGet(String tag, Map<String, String> requestMap) {
    }

    /**
     * 加载网络数据
     */
    protected void loadNetDataPost(String tag) {
    }

    /**
     * 加载成功
     */
    protected void loadDataSuccess(String tag,String response) {
    }

    /**
     * 加载网络数据失败
     */
    protected void loadDataFailed(String tag, int errorCode, String errorMsg) {
    }


    protected void showCenterView() {
        loadingLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.GONE);
        emptyLay.setVisibility(View.GONE);
        contentLay.setVisibility(View.VISIBLE);
    }

    protected void showLoadingView() {
        loadingLay.setVisibility(View.VISIBLE);
        errorLay.setVisibility(View.GONE);
        emptyLay.setVisibility(View.GONE);
        contentLay.setVisibility(View.GONE);
    }

    protected void showErrorView() {
        loadingLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.VISIBLE);
        emptyLay.setVisibility(View.GONE);
        contentLay.setVisibility(View.GONE);
    }

    protected void showEmptyView() {
        loadingLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.GONE);
        emptyLay.setVisibility(View.VISIBLE);
        contentLay.setVisibility(View.GONE);
    }
}

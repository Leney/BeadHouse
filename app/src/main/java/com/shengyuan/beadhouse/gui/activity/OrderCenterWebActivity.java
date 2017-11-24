package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

/**
 * 订单中心网页加载Activity
 * Created by lilijun on 2016/12/9.
 */
public class OrderCenterWebActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private String url;
    private ProgressBar bar;

    private TextView oldPayMoney,realPayMoney,discountTips,payBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_center;
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        if(TextUtils.isEmpty(title)){
            title =getResources().getString(R.string.web_page);
        }
        baseTitle.setTitleName(title);
        baseTitle.setRightImgRes(R.mipmap.order_record_btn);
        baseTitle.setRightImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.startActivity(OrderCenterWebActivity.this,"http://www.baidu.com",getResources().getString(R.string.order_records));
            }
        });

        bar = (ProgressBar) findViewById(R.id.order_center_progress);
        webView = (WebView) findViewById(R.id.order_center_web_view);
        oldPayMoney = (TextView) findViewById(R.id.order_center_need_pay_money_old);
        realPayMoney = (TextView) findViewById(R.id.order_center_need_pay_money_real);
        discountTips = (TextView) findViewById(R.id.order_center_discount_tips);
        payBtn = (TextView) findViewById(R.id.order_center_pay_btn);
        payBtn.setOnClickListener(this);

        WebSettings ws = webView.getSettings();
        ws.setDefaultTextEncodingName("uft-8");
        ws.setLoadWithOverviewMode(true);
        ws.setJavaScriptEnabled(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm
                    .TEXT_AUTOSIZING);
        } else {
            ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        if(!url.startsWith("http://")){
            url = "http://"+url;
        }
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 加载完成
                    // 不显示进度条了
                    bar.setVisibility(View.GONE);
                    showCenterView();
                } else {
                    // 加载中
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        showCenterView();

//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                webView.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request,
//                                        WebResourceError error) {
//                super.onReceivedError(view, request, error);
//            }
//        });
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        webView.reload();
    }

    @Override
    public void onBackPressed() {
        if(webView != null && webView.canGoBack()){
            webView.goBack();// 返回前一个页面
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        try {
            // 反射方法 暂停webView
            webView.getClass().getMethod("onPause").invoke(webView,(Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }

    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, OrderCenterWebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_center_pay_btn:
                // 支付按钮
                break;
        }
    }
}

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

import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.retrofit.HttpConstance;

/**
 * 订单中心网页加载Activity
 * Created by lilijun on 2016/12/9.
 */
public class OrderCenterWebActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private String url;
    private ProgressBar bar;

//    private TextView oldPayMoney, realPayMoney, discountTips, payBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_center;
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        if (TextUtils.isEmpty(title)) {
            title = getResources().getString(R.string.web_page);
        }
        baseTitle.setTitleName(title);
        baseTitle.setRightImgRes(R.mipmap.order_record_btn);
        baseTitle.setRightImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WebActivity.startActivity(OrderCenterWebActivity.this,"http://www.baidu.com",getResources().getString(R.string.order_records));
                WebActivity.startActivity(OrderCenterWebActivity.this, HttpConstance.BASE_URL + "/api/order-record?token=" + BHApplication.getInstance().getToken(), getResources().getString(R.string.order_records));
            }
        });

        bar = (ProgressBar) findViewById(R.id.order_center_progress);
        webView = (WebView) findViewById(R.id.order_center_web_view);
//        oldPayMoney = (TextView) findViewById(R.id.order_center_need_pay_money_old);
//        realPayMoney = (TextView) findViewById(R.id.order_center_need_pay_money_real);
//        discountTips = (TextView) findViewById(R.id.order_center_discount_tips);
//        payBtn = (TextView) findViewById(R.id.order_center_pay_btn);
//        payBtn.setOnClickListener(this);

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
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }

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


//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                if (!(url.startsWith("http") || url.startsWith("https"))) {
//                    return true;
//                }
//
//                /**
//                 * 推荐采用的新的二合一接口(payInterceptorWithUrl),只需调用一次
//                 */
//                final PayTask task = new PayTask(OrderCenterWebActivity.this);
//                boolean isIntercepted = task.payInterceptorWithUrl(url, true, new H5PayCallback() {
//                    @Override
//                    public void onPayResult(final H5PayResultModel result) {
//                        // 支付结果返回
//                        final String url = result.getReturnUrl();
//                        if (!TextUtils.isEmpty(url)) {
//                            OrderCenterWebActivity.this.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    view.loadUrl(url);
//                                }
//                            });
//                        }
//                    }
//                });
//
//                Log.i("llj","是否拦截成功！isIntercepted----->>"+isIntercepted);
//                /**
//                 * 判断是否成功拦截
//                 * 若成功拦截，则无需继续加载该URL；否则继续加载
//                 */
//                if (!isIntercepted) {
//                    view.loadUrl(url);
//                }
//                return true;
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request,
//                                        WebResourceError error) {
//                super.onReceivedError(view, request, error);
//            }
//        });
//        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
        showCenterView();
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            if (!(url.startsWith("http") || url.startsWith("https"))) {
                return true;
            }

            /**
             * 推荐采用的新的二合一接口(payInterceptorWithUrl),只需调用一次
             */
            final PayTask task = new PayTask(OrderCenterWebActivity.this);
            boolean isIntercepted = task.payInterceptorWithUrl(url, true, new H5PayCallback() {
                @Override
                public void onPayResult(final H5PayResultModel result) {
                    final String url=result.getReturnUrl();
                    if(!TextUtils.isEmpty(url)){
                        OrderCenterWebActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.loadUrl(url);
                            }
                        });
                    }
                }
            });

            /**
             * 判断是否成功拦截
             * 若成功拦截，则无需继续加载该URL；否则继续加载
             */
            if(!isIntercepted)
                view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        webView.reload();
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        try {
            // 反射方法 暂停webView
            webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
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
//        switch (v.getId()) {
//            case R.id.order_center_pay_btn:
//                // 支付按钮
//                break;
//        }
    }
}

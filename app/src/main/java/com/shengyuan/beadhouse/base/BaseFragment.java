package com.shengyuan.beadhouse.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.util.NetUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dell on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment {
    protected RelativeLayout loadingLay;
    protected RelativeLayout errorLay;
    protected RelativeLayout emptyLay;
    protected RelativeLayout contentLay;
    private RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base,container,false);
        loadingLay = rootView.findViewById(R.id.base_fragment_loading_lay);
        errorLay = rootView.findViewById(R.id.base_fragment_error_lay);
        emptyLay = rootView.findViewById(R.id.base_fragment_empty_lay);
        contentLay = rootView.findViewById(R.id.base_fragment_content_lay);
        contentLay.addView(View.inflate(getActivity(), getLayoutId(), null), mParams);
        initView(rootView);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View rootView);

    /**
     * 加载网络数据
     */
    protected void loadNetDataGet(final String tag, Map<String, Object> requestMap) {
        if (requestMap == null) requestMap = new HashMap<>();
        Observable.just(requestMap).map(new Func1<Map<String, Object>, String>() {
            @Override
            public String call(Map<String, Object> stringObjectMap) {
                String url = NetUtil.getURL(Constance.MAIN_URL, stringObjectMap);
                Request request = null;
                if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                    request = new Request.Builder()
                            .url(url)
                            .addHeader("Connection", "close")
                            .build();
                } else {
                    request = new Request.Builder()
                            .url(url)
                            .build();
                }
                try {
                    Response response = BHApplication.getInstance().getOkHttpClient().newCall(request).execute();
                    return response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            loadDataSuccess(tag, s);
                        } else {
                            loadDataFailed(tag, -1, "无法连接到服务器");
                        }
                    }
                });

    }

    /**
     * 加载网络数据,Post方式
     */
    protected void loadNetDataPost(final String tag, Map<String, Object> requestMap) {
        if (requestMap == null) requestMap = new HashMap<>();
        Observable.just(requestMap).map(new Func1<Map<String, Object>, String>() {
            @Override
            public String call(Map<String, Object> objectMap) {
                RequestBody requestBody = NetUtil.getPostBuilder(objectMap).build();
                String url = Constance.MAIN_URL + tag;
                Request request = null;
                if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                    request = new Request.Builder()
                            .url(url)
                            .addHeader("Connection", "close")
                            .post(requestBody)
                            .build();
                } else {
                    request = new Request.Builder()
                            .url(Constance.MAIN_URL + tag)
                            .build();
                }

                try {
                    Response response = BHApplication.getInstance().getOkHttpClient().newCall(request).execute();
                    return response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            loadDataSuccess(tag, s);
                        } else {
                            loadDataFailed(tag, -1, "无法连接到服务器");
                        }
                    }
                });
    }

    /**
     * 加载成功
     */
    protected void loadDataSuccess(String tag, String response) {
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

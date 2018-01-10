package com.shengyuan.beadhouse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.retrofit.RetrofitClient;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by dell on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment {

    /**
     *  rx的订阅者管理器
     */
    protected CompositeSubscription compositeSubscription;
    protected RetrofitClient retrofitClient;
//    protected SQLBriteProvider sqlBriteProvider;
    // TODO 临时写死默认的token
    protected String token = "7yts73rm1510121415981";

    protected RelativeLayout loadingLay;
    protected RelativeLayout errorLay;
    protected RelativeLayout emptyLay;
    protected RelativeLayout contentLay;
    private RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModeConfig();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_base,container,false);
        loadingLay = rootView.findViewById(R.id.base_fragment_loading_lay);
        errorLay = rootView.findViewById(R.id.base_fragment_error_lay);
        emptyLay = rootView.findViewById(R.id.base_fragment_empty_lay);
        contentLay = rootView.findViewById(R.id.base_fragment_content_lay);
        contentLay.addView(View.inflate(getActivity(), getLayoutId(), null), mParams);
        showLoadingView();
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(rootView);
    }

    /**
     * 初始化Model配置
     */
    void initModeConfig() {
        compositeSubscription = new CompositeSubscription();
        this.retrofitClient = RetrofitClient.getInstance();
//        this.sqlBriteProvider = SQLBriteProvider.getInstance(getActivity());
//        queryPersonMsg();
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View rootView);

    protected void tryAgain() {
        showLoadingView();
    }

    @Override
    public void onDestroy() {
        if (compositeSubscription!=null){
            compositeSubscription.clear();
        }
        super.onDestroy();
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

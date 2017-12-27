package com.shengyuan.beadhouse.gui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.util.ActivityUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by ${xingen} on 2017/11/7.
 * 首页界面
 */

public class HomeActivity extends AppCompatActivity {
    private CompositeSubscription compositeSubscription;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // PhotoLoaderUtil.display(this,R.mipmap.home_bg,R.mipmap.home_bg,imageView);
        ActivityUtils.addActivity(this);
        this.compositeSubscription = new CompositeSubscription();
        executeTask();
        executeDelayTask();
    }

    /**
     * 查询数据库
     */
    private void executeTask() {
        Subscription subscription = UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean bean) {
                if (bean == null) return;
                token = bean.getToken();
                BHApplication.getInstance().setToken(token);
            }
        });
        this.compositeSubscription.add(subscription);
    }

    /**
     * 检查是否登入过：
     * <p>
     * 1. token为空，未登录。
     *
     * @return
     */
    private boolean checkLogin() {
        return !TextUtils.isEmpty(token);
    }

    /**
     * 执行一个定时任务
     */
    private void executeDelayTask() {
        Subscription subscription = Observable
                .timer(2, TimeUnit.SECONDS, Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (checkLogin()) {
                            MainActivity.startActivity(HomeActivity.this);
                        } else {
                            LoginActivity.startActivity(HomeActivity.this);
                        }
                        HomeActivity.this.finish();
                    }
                });
        this.compositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
        this.compositeSubscription.clear();
    }
}

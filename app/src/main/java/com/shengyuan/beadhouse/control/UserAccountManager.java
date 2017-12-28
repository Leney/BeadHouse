package com.shengyuan.beadhouse.control;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.db.control.UserDBManager;
import com.shengyuan.beadhouse.model.LoginBean;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 当前用户账号管理类
 * Created by llj on 2017/12/27.
 */

public class UserAccountManager {
    private static UserAccountManager instance = null;

    private UserAccountManager() {
    }

    public static UserAccountManager getInstance() {
        if (instance == null) {
            synchronized (UserAccountManager.class) {
                if (instance == null) {
                    instance = new UserAccountManager();
                }
            }
        }
        return instance;
    }


    /**
     * 保存登陆用户的信息到数据库中
     *
     * @param bean
     */
    public Subscription saveUserAccountToDB(final LoginBean bean, Action1<Object> action1) {
        // 设置全局token
        BHApplication.getInstance().setToken(bean.getToken());
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                // 保存登陆用户信息到数据库中
                UserDBManager.getInstance().insertNewUser(bean);
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 从数据库中查找当前登陆的用户信息
     */
    public Subscription queryCurLoginAccount(Action1<LoginBean> action1) {
        return Observable.create(new Observable.OnSubscribe<LoginBean>() {
            @Override
            public void call(Subscriber<? super LoginBean> subscriber) {
                subscriber.onNext(UserDBManager.getInstance().query());
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 完善用户个人信息
     *
     * @param name
     * @param sex
     * @param cardId
     * @return
     */
    public Subscription perfectUserInfo(final String name, final String sex, final String cardId, Action1<LoginBean> action1) {
        return Observable.create(new Observable.OnSubscribe<LoginBean>() {
            @Override
            public void call(Subscriber<? super LoginBean> subscriber) {
                subscriber.onNext(UserDBManager.getInstance().perfectUserInfo(name, sex, cardId));
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 清除登陆信息
     *
     * @return
     */
    public Subscription clear(Action1<Object> action1) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                UserDBManager.getInstance().delete();
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}

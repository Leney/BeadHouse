package com.shengyuan.beadhouse.retrofit;

import android.content.Context;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.model.RoomInfoBean2;
import com.shengyuan.beadhouse.okhttp.OkHttpProvider;
import com.shengyuan.beadhouse.rxjava.SubscribeUtils;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;

/**
 * Retrofit接口请求管理类
 * Created by llj on 2017/11/14.
 */

public class RetrofitClient {
    private final Retrofit retrofit;
    private final ApiService apiService;
    private static RetrofitClient instance;
    private final OkHttpClient okHttpClient;
    private final String baseUrl;
    private Context appContext;

    /**
     * 配置Retrofit,OkHttp3
     */
    private RetrofitClient() {
        this.okHttpClient = OkHttpProvider.provideOkHttpClient();
        this.baseUrl = HttpConstance.BASE_URL;
        retrofit = new Retrofit.Builder()
                .client(this.okHttpClient)
                .baseUrl(this.baseUrl)
                // 自定义Gson解析
//                .addConverterFactory(SpecifiedJsonConverter.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.apiService = createService(ApiService.class);
        this.appContext = BHApplication.getInstance();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    /**
     * 指定创建对应的ServiceInterface.防止基本的Service接口不适合
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T createService(final Class<T> service) {
        return this.retrofit.create(service);
    }

    /**
     * 通用方法：将回调接口对象，传递到Subscriber
     *
     * @param subscriber
     * @param <T>
     * @return
     */
    private <T> BaseSubscriber<T> toSubscriber(ResponseResultListener<T> subscriber) {
        return new BaseSubscriber(this.appContext, subscriber);
    }


    /**
     * 获取抢答列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getRoomList(Map<String, Object> params, ResponseResultListener<RoomInfoBean2> subscriber) {
        return this.apiService.getRoomList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }


    /**
     * 获取短信验证码
     *
     * @param phone 手机号
     * @param type  类型(regist=注册，forget=忘记密码，telchange=修改手机号，login=短信登陆)
     * @return
     */
    public Subscription getMessageCode(String phone, String type) {
        return this.apiService.getMessageCode(phone, type).compose(SubscribeUtils.createTransformer()).subscribe();
    }

    /**
     * 注册账号
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription register(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.register(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 登陆
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription login(Map<String, Object> params, ResponseResultListener<LoginBean> subscriber) {
        return this.apiService.login(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取登陆用户的基本信息
     *
     * @param subscriber
     * @return
     */
    public Subscription getLoginInfo(ResponseResultListener subscriber) {
        return this.apiService.getLoginInfo().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 找回密码，设置新的密码
     *
     * @param subscriber
     * @return
     */
    public Subscription setNewPassword(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.setNewPassword(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 找回密码，验证验证码
     *
     * @param subscriber
     * @return
     */
    public Subscription verifyCode(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.verifyCode(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取关注老人列表
     *
     * @param subscriber
     * @return
     */
    public Subscription getCareOldManList(ResponseResultListener<CareOldManListBean> subscriber) {
        return this.apiService.getCareOldManList().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }
}

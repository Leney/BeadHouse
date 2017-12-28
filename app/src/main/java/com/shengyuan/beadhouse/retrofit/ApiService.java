package com.shengyuan.beadhouse.retrofit;

import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.model.RoomInfoBean2;
import com.shengyuan.beadhouse.model.SearchOldManResultBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by llj on 2017/11/14.
 */

public interface ApiService {

    // TODO 注释掉的地方是范例
//    /**
//     * 获取我的计划列表
//     *
//     * @param params
//     * @return
//     */
//    @GET("/s_plan/get_my_s_plan_list")
//    Observable<HttpResult<MyPlanBean>> getMyPlanList(@QueryMap Map<String, Object> params);

//    @GET("{path}")
//        //获取活动流程详情
//    Observable<HttpResult<ActivityFlowDetailBean>> getActivityFlowDetailBaseData(@Path("path") String url, @QueryMap Map<String, String> options);

//    @GET("/user/login")
//        //登录
//    Call<HttpResult<LoginModelBean>> getLoginBaseData(@Query("loginName") String loginName, @Query("userPass") String userPass);


    /**
     * 获取抢答大厅列表数据
     *
     * @param params
     * @return
     */
    @GET("/contest/get_room_list")
    Observable<HttpResult<RoomInfoBean2>> getRoomList(@QueryMap Map<String, Object> params);

    /**
     * 获取验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("/api/confirme/")
    Observable<HttpResult> getMessageCode(@Field("username") String phone, @Field("type") String type);

    /**
     * 注册
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/api/regist/")
    Observable<HttpResult> register(@FieldMap Map<String, Object> params);

    /**
     * 登陆
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/api/login/")
    Observable<HttpResult<LoginBean>> login(@FieldMap Map<String, Object> params);

    /**
     * 获取当前登陆的用户信息
     *
     * @return
     */
    @GET("/api/get_register/")
    Observable<HttpResult> getLoginInfo();

    /**
     * 找回密码，设置新的密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/forget-password/")
    Observable<HttpResult> setNewPassword(@FieldMap Map<String, Object> params);

    /**
     * 找回密码，验证验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/judge_confirm/")
    Observable<HttpResult> verifyCode(@FieldMap Map<String, Object> params);

    /**
     * 找回密码，验证验证码
     *
     * @return
     */
    @GET("/api/get_focus/")
    Observable<HttpResult<CareOldManListBean>> getCareOldManList();

    /**
     * 根据身份证号搜索老人
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/select-olders/")
    Observable<HttpResult<SearchOldManResultBean>> searchOldManByCardId(@Field("ID_number") String cardId);

    /**
     * 添加关注老人
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/add-attention/")
    Observable<HttpResult> addCareOldMan(@FieldMap Map<String, Object> params);

    /**
     * 完善个人资料
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/perfect-information/")
    Observable<HttpResult> perfectPersonalInfo(@FieldMap Map<String, Object> params);
}

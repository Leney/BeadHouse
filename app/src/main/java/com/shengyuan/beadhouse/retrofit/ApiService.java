package com.shengyuan.beadhouse.retrofit;

import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.CouponBean;
import com.shengyuan.beadhouse.model.GuardianBean;
import com.shengyuan.beadhouse.model.LocationAndHeartRateBean;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.model.PhysicBean;
import com.shengyuan.beadhouse.model.RemoteServiceBean;
import com.shengyuan.beadhouse.model.RoomInfoBean2;
import com.shengyuan.beadhouse.model.ScheduleBean;
import com.shengyuan.beadhouse.model.SearchOldManResultBean;
import com.shengyuan.beadhouse.model.ServicePackageBean;
import com.shengyuan.beadhouse.model.UploadHeaderResultBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
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
     * 登出
     * @return
     */
    @GET("/api/logout/")
    Observable<HttpResult> loginOut();

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

    /**
     * 修改密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/change-password/")
    Observable<HttpResult> modifyPassword(@FieldMap Map<String, Object> params);

    /**
     * 上传图片
     *
     * @return
     */
    @Multipart
    @POST("/api/upload_pic/")
    Observable<HttpResult<UploadHeaderResultBean>> uploadPicture(@Part MultipartBody.Part file);

    /**
     * 获取老人生理数据
     *
     * @return
     */
    @GET("/api/physiological-data")
    Observable<HttpResult<PhysicBean>> getPhysicData(@Query("ID_number") String cardId);

    /**
     * 获取服务套餐列表
     *
     * @return
     */
    @GET("/api/old_pack")
    Observable<HttpResult<List<ServicePackageBean>>> getServicePackageList(@Query("ID_number") String cardId);

    /**
     * 获取老人监护人列表
     *
     * @return
     */
    @GET("/api/get_guars")
    Observable<HttpResult<List<GuardianBean>>> getGuardianListForOldMan(@Query("ID_number") String cardId);

    /**
     * 根据日期获取老人照护计划
     *
     * @return
     */
    @GET("/api/time_care_plan")
    Observable<HttpResult<List<ScheduleBean>>> getCarePlanByDate(@Query("ID_number") String cardId,@Query("date") String date);

    /**
     * 获取我的优惠券列表
     *
     * @return
     */
    @GET("/api/get_coupon")
    Observable<HttpResult<List<CouponBean>>> getMyCouponList();

    /**
     * 兑换优惠券
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/exchange_coupon/")
    Observable<HttpResult> exchangeCouponByCode(@Field("number") String code);

    /**
     * 获取远程看护信息
     *
     * @return
     */
    @GET("/api/nurse")
    Observable<HttpResult<List<RemoteServiceBean>>> getNurse(@Query("ID_number") String cardId);

    /**
     * 修改手机号码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/change-username/")
    Observable<HttpResult> modifyPhone(@FieldMap Map<String, Object> params);

    /**
     * 获取手环位置信息和心率信息
     *
     * @return
     */
    @GET("/api/bracelet")
    Observable<HttpResult<LocationAndHeartRateBean>> getLocationAndHeartRateInfo(@Query("ID_number") String cardId);
}

package com.shengyuan.beadhouse.retrofit;

import com.shengyuan.beadhouse.model.RoomInfoBean2;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Observable<HttpResult<RoomInfoBean2>> getRoomList(@QueryMap Map<String,Object> params);

    @POST("/api/confirme/")
    Observable<HttpResult> getMessageCode(@Query("username") String phone);
}

package com.shengyuan.beadhouse.gui.activity;

import android.util.Log;
import android.view.View;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.model.RoomInfoBean2;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/11/2.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        findViewById(R.id.test_get_request_btn).setOnClickListener(this);
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_get_request_btn:
//                Map<String,Object> map = new HashMap<>();
//                map.put("skuIds","J_954086");
//                map.put("type",1);
//                loadNetDataGet("testTag",map);


//                RegisterActivity.startActivity(TestActivity.this);
//                LoginActivity.startActivity(TestActivity.this);



                // 请求网络
                getNetworkDemo();


                break;
        }
    }


    /**
     * 调用网络请求使用范例
     */
    private void getNetworkDemo(){
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("pageIndex", 1);
        params.put("pageSize", 10);
        params.put("resGrade", 1);
        params.put("resKind", 1);
        retrofitClient.getRoomList(params, new ResponseResultListener<RoomInfoBean2>() {
            @Override
            public void success(RoomInfoBean2 roomInfoBean2) {
                Log.i("llj", "请求列表数据成功!!!");
            }

            @Override
            public void failure(CommonException e) {
                Log.e("llj", "请求列表数据失败!!!");
            }
        });
    }

}

package com.shengyuan.beadhouse.gui.activity;

import android.util.Log;
import android.view.View;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_get_request_btn:
//                Map<String,Object> map = new HashMap<>();
//                map.put("skuIds","J_954086");
//                map.put("type",1);
//                loadNetDataGet("testTag",map);
                RegisterActivity.startActivity(TestActivity.this);
                break;
        }
    }

    @Override
    protected void loadDataSuccess(String tag, String response) {
        super.loadDataSuccess(tag, response);
        Log.i("llj","tag--------->>"+tag);
        Log.i("llj","response--------->>"+response);
    }
}

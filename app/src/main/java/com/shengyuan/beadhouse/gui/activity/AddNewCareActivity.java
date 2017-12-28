package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.Constance;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.dialog.SelectRelationshipDialog;
import com.shengyuan.beadhouse.gui.dialog.WaitingDialog;
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.model.SearchOldManResultBean;
import com.shengyuan.beadhouse.retrofit.CommonException;
import com.shengyuan.beadhouse.retrofit.ResponseResultListener;
import com.shengyuan.beadhouse.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 新增关注老人界面
 * Created by dell on 2017/11/27.
 */

public class AddNewCareActivity extends BaseActivity implements View.OnClickListener, SelectRelationshipDialog.OnAddSureListener {
    private LinearLayout searchLay;
    private ConstraintLayout resultLay;
    private EditText searchInput;

    private ImageView icon;
    private TextView name, age, sex, cardNo;

    private TextView searchAndAddBtn;

    /**
     * 搜索出结果的老人信息对象
     */
    private SearchOldManResultBean.TheolderBean resultTheolderBean;

    /**
     * 标识当前页是否显示结果页面
     */
    private boolean isResult = false;

    /**
     * 选择关系dialog
     */
    private SelectRelationshipDialog dialog;

    private WaitingDialog waitingDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_new_care;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.add_new_care_old_man));
        searchLay = (LinearLayout) findViewById(R.id.add_new_care_search_lay);
        resultLay = (ConstraintLayout) findViewById(R.id.add_new_care_search_result_lay);
        searchInput = (EditText) findViewById(R.id.add_new_care_card_input);
        icon = (ImageView) findViewById(R.id.add_new_care_search_result_icon);
        name = (TextView) findViewById(R.id.add_new_care_search_result_name);
        age = (TextView) findViewById(R.id.add_new_care_search_result_age);
        sex = (TextView) findViewById(R.id.add_new_care_search_result_sex);
        cardNo = (TextView) findViewById(R.id.add_new_care_search_result_card_no);
        searchAndAddBtn = (TextView) findViewById(R.id.add_new_care_search_now_btn);
        searchAndAddBtn.setOnClickListener(this);
        setVisibleLay();
        showCenterView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new_care_search_now_btn:
                if (isResult) {
                    // 当前是结果页面，点击则添加----> 弹框选择关系
                    if (dialog == null) {
                        dialog = new SelectRelationshipDialog(AddNewCareActivity.this);
                        dialog.setListener(this);
                    }
                    dialog.show();
                } else {
                    // 当前页是搜索页面，点击则搜索
                    String no = searchInput.getText().toString();
                    if (no.length() != 18) {
                        Toast.makeText(AddNewCareActivity.this, getResources().getString(R.string.input_right_card_no), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    searchOldManByCardId(no);
                }
                break;
        }
    }

    private void setVisibleLay() {
        if (isResult) {
            searchLay.setVisibility(View.GONE);
            resultLay.setVisibility(View.VISIBLE);
            searchAndAddBtn.setText(getResources().getString(R.string.add_care));
        } else {
            searchLay.setVisibility(View.VISIBLE);
            resultLay.setVisibility(View.GONE);
            searchAndAddBtn.setText(getResources().getString(R.string.search_now));
        }
    }


    /**
     * 设置搜索结果视图
     */
    private void setSearchResultView(SearchOldManResultBean.TheolderBean bean) {
        GlideLoader.loadNetWorkResource(this, bean.getPhoto(), icon, R.mipmap.default_lis_head_icon, false);
        name.setText(bean.getName());
        age.setText(bean.getAge() + getResources().getString(R.string.age));
        sex.setText(bean.getSex());
        cardNo.setText(bean.getID_number());
    }

    /**
     * 通过身份证id搜索老人
     *
     * @param cardId
     */
    private void searchOldManByCardId(String cardId) {
        if (waitingDialog == null) {
            waitingDialog = new WaitingDialog(AddNewCareActivity.this);
        }
        waitingDialog.show();
        retrofitClient.searchOldManByCardId(cardId, new ResponseResultListener<SearchOldManResultBean>() {
            @Override
            public void success(SearchOldManResultBean searchOldManResultBean) {
                resultTheolderBean = searchOldManResultBean.getTheolder();
                isResult = true;
                setVisibleLay();
                // 设置搜索结果
                setSearchResultView(searchOldManResultBean.getTheolder());
                waitingDialog.dismiss();
            }

            @Override
            public void failure(CommonException e) {
                waitingDialog.dismiss();
                ToastUtils.showToast(e.getErrorMsg());
            }
        });
    }

    /**
     * 确定添加关注老人
     */
    private void sureAdd(String cardId, String relation) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID_number", cardId);
        params.put("relation", relation);
        retrofitClient.addCareOldMan(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                CareOldManListBean.FocusListBean focusListBean = new CareOldManListBean.FocusListBean();
                focusListBean.setID_number(resultTheolderBean.getID_number());
                focusListBean.setAddress(resultTheolderBean.getAddress());
                focusListBean.setAddtime(resultTheolderBean.getAddtime());
                focusListBean.setAge(resultTheolderBean.getAge());
                focusListBean.setArea(resultTheolderBean.getArea());
                focusListBean.setBody(resultTheolderBean.getBody());
                focusListBean.setCell_phone(resultTheolderBean.getCell_phone());
                focusListBean.setFix_phone(resultTheolderBean.getFix_phone());
                focusListBean.setIs_delete(resultTheolderBean.getIs_delete());
                focusListBean.setName(resultTheolderBean.getName());
                focusListBean.setPhoto(resultTheolderBean.getPhoto());
                focusListBean.setSex(resultTheolderBean.getSex());

                Intent intent = new Intent(Constance.ACTION_CARE_NEW_OLD_MAN);
                intent.putExtra("FocusListBean", focusListBean);
                sendBroadcast(intent);
                ToastUtils.showToast(getResources().getString(R.string.care_old_man_success));
                finish();
            }

            @Override
            public void failure(CommonException e) {

            }
        });
    }

    @Override
    public void onAddSure(String relation) {
        // 确定添加按钮
        sureAdd(resultTheolderBean.getID_number(), relation);
    }

    @Override
    public void onBackPressed() {
        if (isResult) {
            isResult = false;
            setVisibleLay();
            return;
        }
        super.onBackPressed();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AddNewCareActivity.class));
    }
}

package com.shengyuan.beadhouse.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ExpandableListView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.gui.adapter.PhysiologyAdapter;
import com.shengyuan.beadhouse.model.PhysiologyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 生理数据界面
 * Created by dell on 2017/11/17.
 */

public class PhysiologyDataActivity extends BaseActivity {
    private ExpandableListView expandableListView;
    private PhysiologyAdapter adapter;
    private List<String> groupList;
    private List<List<PhysiologyBean>> childList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_physiology;
    }

    @Override
    protected void initView() {

        baseTitle.setTitleName(getResources().getString(R.string.physiology_date));

        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        groupList.add("2017年10月27日");
        groupList.add("2017年10月26日");
        groupList.add("2017年10月25日");
        groupList.add("2017年10月24日");
        groupList.add("2017年10月23日");

        for (int i = 0; i < 5; i++) {
            List<PhysiologyBean> list = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                PhysiologyBean bean = new PhysiologyBean();
                bean.id = j;
                if (j == 0) {
                    bean.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510861376494&di=648ad69452079f30fe39afb29a9d91ac&imgtype=0&src=http%3A%2F%2Fimg36.photophoto.cn%2F20150711%2F0017030563348585_s.jpg";
                    bean.name = "体重";
                    bean.value = (75 + j) + "kg";
                } else if (j == 1) {
                    bean.icon = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3458948182,3989316910&fm=27&gp=0.jpg";
                    bean.name = "体温";
                    bean.value = (36 + j) + ".8°";
                } else if (j == 2) {
                    bean.icon = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1400645146,423052994&fm=27&gp=0.jpg";
                    bean.name = "心率";
                    bean.value = (70 + j) + "次/分";
                } else if (j == 3) {
                    bean.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510861812241&di=7921c702401f9d3e91e2771eebe144b0&imgtype=0&src=http%3A%2F%2Fpic27.photophoto.cn%2F20130508%2F0010023589073820_b.jpg";
                    bean.name = "血压";
                    bean.value = (120 + j) + "80mmHg";
                } else if (j == 4) {
                    bean.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511456604&di=feec64d738ed2aa9561e6a1f3f315c1c&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa8773912b31bb0519b401b0b3c7adab44aede0a9.jpg";
                    bean.name = "血糖";
                    bean.value = (6 + j) + ".6mmol/L";
                } else if (j == 5) {
                    bean.icon = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=856713177,3039623004&fm=200&gp=0.jpg";
                    bean.name = "血脂";
                    bean.value = (160 + j) + "mmol";
                }
                list.add(bean);
            }
            childList.add(list);
        }

        expandableListView = (ExpandableListView) findViewById(R.id.physiology_expandable_list_view);
        adapter = new PhysiologyAdapter(groupList, childList);
        expandableListView.setAdapter(adapter);

        int count = expandableListView.getCount();
        for (int i = 0; i < count; i++) {
            expandableListView.expandGroup(i);
        }
        showCenterView();

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PhysiologyDataActivity.class));
    }
}
